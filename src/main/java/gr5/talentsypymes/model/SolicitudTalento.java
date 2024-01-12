package gr5.talentsypymes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
@Entity
@Table(name = "solicitudestalento")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class SolicitudTalento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private Date fecha;
    @NotNull
    private String comentarios;
    @NotNull
    private String archivo;

    @OneToOne
    @JoinColumn(name = "talentoId")
    private VacanteTalento vacanteTalento;

    @OneToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

}
