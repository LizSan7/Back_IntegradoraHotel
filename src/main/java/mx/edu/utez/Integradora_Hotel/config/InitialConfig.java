package mx.edu.utez.Integradora_Hotel.config;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.Integradora_Hotel.model.role.Role;
import mx.edu.utez.Integradora_Hotel.model.role.RoleRepository;
import mx.edu.utez.Integradora_Hotel.model.usuario.Usuario;
import mx.edu.utez.Integradora_Hotel.model.usuario.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor

public class InitialConfig implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;

    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public void run (String... args) throws Exception{
        Role adminRole = getOrSaveRole(new Role("GERENTE_ROLE"));
        getOrSaveRole(new Role("RECEPCIONISTA_ROLE"));
        getOrSaveRole(new Role("CLIENTE_ROLE"));

        Usuario user = getOrSaveUser(
                new Usuario("Lizbeth", "Santibañez", "Cruz", "lizz@gmail.com", encoder.encode("Lizz"), true)
        );
        saveUserRoles(user.getId_usuario(), adminRole.getId_role());
    }

    @Transactional
    public Role getOrSaveRole(Role role) {
        Optional<Role> foundRole = roleRepository.findByNombre(role.getNombre());
        return foundRole.orElseGet(() -> {
            roleRepository.saveAndFlush(role);
            return roleRepository.findByNombre(role.getNombre()).orElse(null);
        });
    }
    @Transactional
    public Usuario getOrSaveUser(Usuario user) {
        Optional<Usuario> foundUser = usuarioRepository.findByCorreo(user.getCorreo());
        return foundUser.orElseGet(() -> {
            Role userRole = user.getRole();
            if (userRole != null) {
                if (userRole.getId_role() == null) {
                    userRole = getOrSaveRole(userRole);
                }
            }
            user.setRole(userRole);
            return usuarioRepository.saveAndFlush(user);
        });
    }
    @Transactional
    public void saveUserRoles(Long userId, Long roleId) {
        Usuario usuario = usuarioRepository.findById(userId).orElse(null);
        if (usuario != null) {
            Role newRole = roleRepository.findById(roleId).orElse(null);
            if (newRole != null) {
                usuario.setRole(newRole);
                usuarioRepository.save(usuario);
            }
        }
    }
}
