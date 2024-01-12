package gr5.talentsypymes.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "perfiles")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPerfil;
    @NotNull
    private String perfil;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "usuarioPerfil",
            joinColumns = @JoinColumn(name = "idPerfil"),
            inverseJoinColumns = @JoinColumn(name = "idUsuario")
    )
    private List<Usuario> usuarios;

}
