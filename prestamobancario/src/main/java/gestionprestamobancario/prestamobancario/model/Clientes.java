package gestionprestamobancario.prestamobancario.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "clientes")
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer clienteId;

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "email")
    private String email;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "direccion")
    private String direccion;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Prestamos> listaPrestamos;
    public Clientes(){}

    public Clientes(String nombre, String email, String telefono, String direccion) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
