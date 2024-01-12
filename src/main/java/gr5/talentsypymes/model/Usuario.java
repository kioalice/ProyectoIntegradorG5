package gr5.talentsypymes.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    @NotNull
    private String username;
    @NotNull
    private String nombre;
    @NotNull
    private String apellido;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private Integer estatus;
    @NotNull
    private Date fechaRegistro;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "usuarioPerfil",
            joinColumns = @JoinColumn(name = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "idPerfil")
    )
    private List<Perfil> perfiles;


    public void agregar(Perfil tempPerfil) {
        if(perfiles== null){
            perfiles = new LinkedList<>();
        }
        perfiles.add(tempPerfil);
    }

    public Usuario() {
    }

    public Usuario(Integer idUsuario, String username, String nombre, String apellido, String email, String password, Integer estatus, Date fechaRegistro, List<Perfil> perfiles) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.estatus = estatus;
        this.fechaRegistro = fechaRegistro;
        this.perfiles = perfiles;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", username='" + username + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", estatus=" + estatus +
                ", fechaRegistro=" + fechaRegistro +
                ", perfiles=" + perfiles +
                '}';
    }
}
