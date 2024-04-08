package mx.edu.utez.Integradora_Hotel.model.reserva;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Integradora_Hotel.model.elemento.Elemento;
import mx.edu.utez.Integradora_Hotel.model.habitacion.Habitacion;
import mx.edu.utez.Integradora_Hotel.model.pago.Pago;
import mx.edu.utez.Integradora_Hotel.model.paquete.Paquete;
import mx.edu.utez.Integradora_Hotel.model.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reserva;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fecha_entrada;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fecha_salida;
    @Column(length = 50, nullable = false)
    private Double total;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fecha_compra;
    @ManyToOne
    @JoinColumn(name = "usuarioreserva_id")
    private Usuario usuarios;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "reserva_elemento", joinColumns = @JoinColumn(name = "reservas_id"),
            inverseJoinColumns = @JoinColumn(name = "elemento_id"))
    private List<Elemento> elementos;

    @ManyToMany(mappedBy = "reservas")
    private List<Habitacion> habitaciones;


    public Reserva(Long id_reserva, LocalDateTime fecha_entrada, LocalDateTime fecha_salida, Double total, LocalDateTime fecha_compra, Usuario usuarios, List<Elemento> elementos, List<Habitacion> habitaciones) {
        this.id_reserva = id_reserva;
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
        this.total = total;
        this.fecha_compra = fecha_compra;
        this.usuarios = usuarios;
        this.elementos = elementos;
        this.habitaciones = habitaciones;
    }
}
