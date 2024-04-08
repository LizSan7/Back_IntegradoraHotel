package mx.edu.utez.Integradora_Hotel.controller.reserva;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Integradora_Hotel.model.elemento.Elemento;
import mx.edu.utez.Integradora_Hotel.model.habitacion.Habitacion;
import mx.edu.utez.Integradora_Hotel.model.pago.Pago;
import mx.edu.utez.Integradora_Hotel.model.paquete.Paquete;
import mx.edu.utez.Integradora_Hotel.model.reserva.Reserva;
import mx.edu.utez.Integradora_Hotel.model.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter

public class ReservaDto {
    private Long id_reserva;
    private LocalDateTime fecha_entrada;
    private LocalDateTime fecha_salida;
    private Double total;
    private LocalDateTime fecha_compra;
    private Long usuarioId; // Cambiado para solo tener el ID
    private List<Long> habitacionIds; // Cambiado para tener IDs de habitaciones
    private List<Long> elementoIds; // Cambiado para tener IDs de elementos

    public Reserva toEntity(Usuario usuario, List<Elemento> elementos, List<Habitacion> habitaciones) {
        Reserva reserva = new Reserva();
        reserva.setId_reserva(this.id_reserva);
        reserva.setFecha_entrada(this.fecha_entrada);
        reserva.setFecha_salida(this.fecha_salida);
        reserva.setTotal(this.total);
        reserva.setFecha_compra(this.fecha_compra);
        reserva.setUsuarios(usuario);
        reserva.setElementos(elementos);
        reserva.setHabitaciones(habitaciones);
        return reserva;
    }
}
