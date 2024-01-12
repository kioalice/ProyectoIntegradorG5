package gr5.talentsypymes.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "categoriaTalento")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class CategoriaTalento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria;
    @NotNull
    private String nombreCategoria;
    @NotNull
    private String descripcion;

}
