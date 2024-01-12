package gr5.talentsypymes.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "VacantePyme")
public class VacantePymes {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer pymeId;
        @NotNull
        private String nombre;
        @NotNull
        private String descripcion;
        @NotNull
        private Date fecha;
        @NotNull
        private Integer aporte;
        @NotNull
        private Integer destacado;
        @NotNull
        private String imagen="no-image.png";
        @NotNull
        private String estatus;
        @NotNull
        private String detalles;
        @NotNull
        private String correo;
        @OneToOne
        @JoinColumn(name = "idCategoria", nullable = false)
        private Categoria categoria;

        public void reset(){
                this.imagen=null;
        }


        public VacantePymes() {
        }

        public VacantePymes(Integer pymeId, String nombre, String descripcion, Date fecha, Integer aporte, Integer destacado, String imagen, String estatus, String detalles, String correo, Categoria categoria) {
                this.pymeId = pymeId;
                this.nombre = nombre;
                this.descripcion = descripcion;
                this.fecha = fecha;
                this.aporte = aporte;
                this.destacado = destacado;
                this.imagen = imagen;
                this.estatus = estatus;
                this.detalles = detalles;
                this.correo = correo;
                this.categoria = categoria;
        }

        public Integer getPymeId() {
                return pymeId;
        }

        public void setPymeId(Integer pymeId) {
                this.pymeId = pymeId;
        }

        public String getNombre() {
                return nombre;
        }

        public void setNombre(String nombre) {
                this.nombre = nombre;
        }

        public String getDescripcion() {
                return descripcion;
        }

        public void setDescripcion(String descripcion) {
                this.descripcion = descripcion;
        }

        public Date getFecha() {
                return fecha;
        }

        public void setFecha(Date fecha) {
                this.fecha = fecha;
        }

        public Integer getAporte() {
                return aporte;
        }

        public void setAporte(Integer aporte) {
                this.aporte = aporte;
        }

        public Integer getDestacado() {
                return destacado;
        }

        public void setDestacado(Integer destacado) {
                this.destacado = destacado;
        }

        public String getImagen() {
                return imagen;
        }

        public void setImagen(String imagen) {
                this.imagen = imagen;
        }

        public String getEstatus() {
                return estatus;
        }

        public void setEstatus(String estatus) {
                this.estatus = estatus;
        }

        public String getDetalles() {
                return detalles;
        }

        public void setDetalles(String detalles) {
                this.detalles = detalles;
        }

        public String getCorreo() {
                return correo;
        }

        public void setCorreo(String correo) {
                this.correo = correo;
        }

        public Categoria getCategoria() {
                return categoria;
        }

        public void setCategoria(Categoria categoria) {
                this.categoria = categoria;
        }


        @Override
        public String toString() {
                return "VacantePymes{" +
                        "pymeId=" + pymeId +
                        ", nombre='" + nombre + '\'' +
                        ", descripcion='" + descripcion + '\'' +
                        ", fecha=" + fecha +
                        ", aporte=" + aporte +
                        ", destacado=" + destacado +
                        ", imagen='" + imagen + '\'' +
                        ", estatus='" + estatus + '\'' +
                        ", detalles='" + detalles + '\'' +
                        ", correo='" + correo + '\'' +
                        ", categoria=" + categoria +
                        '}';
        }
}
