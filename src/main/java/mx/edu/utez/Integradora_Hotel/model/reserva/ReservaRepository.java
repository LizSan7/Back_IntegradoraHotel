package mx.edu.utez.Integradora_Hotel.model.reserva;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findAllByUsuariosId(Long usuarioId);
}
