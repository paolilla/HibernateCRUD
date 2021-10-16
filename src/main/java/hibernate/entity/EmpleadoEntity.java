package hibernate.entity;
import java.io.Serializable;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name="Empleado")
public class EmpleadoEntity implements  Serializable{
 
    private static final long serialVersionUID = 8867565466924455293L;
 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer idEmpleado;
     
    @Column(name="nombre")
    private String nombreEmpleado;

    @Column(name="apellido")
    private String apellidoEmpleado;

    @Column(name="cargo")
    private String cargoEmpleado;

    @Column(name="edad")
    private Integer edadEmpleado;

    @Column(name="direccion")
    private String direccionEmpleado;

    @Column(name="telefono")
    private String telefonoEmpleado;
 
    public Integer getIdEmpleado() {
        return idEmpleado;
    }
 
    public void setIdEmpleado(Integer id) {
        this.idEmpleado = id;
    }
 
    public String getNombreEmpleado() {
        return nombreEmpleado;
    }
 
    public void setNombreEmpleado(String nombre) {
        this.nombreEmpleado = nombre;
    }

    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }
 
    public void setApellidoEmpleado(String apellido) {
        this.apellidoEmpleado = apellido;
    }

    public String getCargoEmpleado() {
        return cargoEmpleado;
    }
 
    public void setCargoEmpleado(String cargo) {
        this.cargoEmpleado = cargo;
    }

    public Integer getEdadEmpleado() {
        return edadEmpleado;
    }
 
    public void setEdadEmpleado(Integer edad) {
        this.edadEmpleado = edad;
    }

    public String getDireccionEmpleado() {
        return direccionEmpleado;
    }
 
    public void setDireccionEmpleado(String direccion) {
        this.direccionEmpleado = direccion;
    }

    public String getTelefonoEmpleado() {
        return telefonoEmpleado;
    }
 
    public void setTelefonoEmpleado(String telefono) {
        this.telefonoEmpleado = telefono;
    }

 
    @Override
    public String toString() {
        return "[ ID = " + idEmpleado + ", Nombre = " + nombreEmpleado + ", Apellido = " + apellidoEmpleado + ", Cargo = " + cargoEmpleado + ", Edad = " + edadEmpleado + ", Dirección = " + direccionEmpleado + ", Teléfono = " + telefonoEmpleado + " ]" ;
    }
}