package gr5.talentsypymes.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
@Setter
@Getter
@Entity
@Table(name = "solicitudes")
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private LocalDate fecha;
    @NotNull
    private String comentarios;
    @NotNull
    private String archivo;

    @OneToOne
    @JoinColumn(name = "pymeId", nullable = false)
    private VacantePymes vacantePymes;

    @OneToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;



    public Solicitud() {
        this.fecha = LocalDate.now();

    }


    @Override
    public String toString() {
        return "Solicitud{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", comentarios='" + comentarios + '\'' +
                ", archivo='" + archivo + '\'' +
                ", vacantePymes=" + vacantePymes +
                ", usuario=" + usuario +
                '}';
    }
}
