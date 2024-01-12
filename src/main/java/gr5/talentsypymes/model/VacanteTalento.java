package gr5.talentsypymes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "VacanteTalento")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class VacanteTalento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer talentoId;
    @NotNull
    private String nombre;
    @NotNull
    private String descripcion;
    @NotNull
    private Date fecha;
    @NotNull
    private Integer destacado;
    @NotNull
    private String imagen="no-image.png";
    @NotNull
    private String estatus;
    @NotNull
    private String detalles;
    @OneToOne
    @JoinColumn(name = "idCategoria", nullable = false)
    private CategoriaTalento categoriaTalento;


}
