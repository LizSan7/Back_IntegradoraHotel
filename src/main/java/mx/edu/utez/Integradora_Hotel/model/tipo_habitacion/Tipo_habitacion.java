package mx.edu.utez.Integradora_Hotel.model.tipo_habitacion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Integradora_Hotel.model.habitacion.Habitacion;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "tipo_habitacion")
public class Tipo_habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tipohab;
    @Column(length = 50, nullable = false)
    private String nombre_habitacion;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "tipoHabitacion")
    private List<Habitacion> habitacions;

    public Tipo_habitacion(Long id_tipohab, String nombre_habitacion, List<Habitacion> habitacions) {
        this.id_tipohab = id_tipohab;
        this.nombre_habitacion = nombre_habitacion;
        this.habitacions = habitacions;
    }
}
