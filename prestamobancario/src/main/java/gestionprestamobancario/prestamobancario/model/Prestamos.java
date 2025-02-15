package gestionprestamobancario.prestamobancario.model;

import gestionprestamobancario.prestamobancario.model.DTO.AprobacionDTO;
import gestionprestamobancario.prestamobancario.model.DTO.ConsultaDTO;
import gestionprestamobancario.prestamobancario.model.DTO.PrestamosDTO;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "prestamos")
public class Prestamos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer prestamoId;
    @Column(name = "monto")
    private BigDecimal monto;
    @Column(name = "interes")
    private BigDecimal interes;
    @Column(name = "duracionmeses")
    private Integer duracionMeses;
    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Clientes cliente;
    /*@OneToMany(mappedBy = "prestamoId")
    private List<HistorialPrestamos> historialPrestamos;*/
    @Transient
    private String estadoInicial= "Pendiente";
    @Transient
    private BigDecimal interesInicial= BigDecimal.valueOf(3.5);

    public Prestamos(){}

    public Prestamos(BigDecimal monto, BigDecimal interes, Integer duracionMeses, String estado, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion, Clientes cliente) {
        this.monto = monto;
        this.interes = interes;
        this.duracionMeses = duracionMeses;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.cliente = cliente;
    }

    public Integer getPrestamoId() {
        return prestamoId;
    }

    public void setPrestamoId(Integer prestamoId) {
        this.prestamoId = prestamoId;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    public Integer getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(Integer duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public static Prestamos solicitudPrestamo(Clientes cliente, PrestamosDTO prestamosDTO){

        Prestamos prestamos = new Prestamos();
        prestamos.setCliente(cliente);
        prestamos.setMonto(prestamosDTO.getMonto());
        prestamos.setDuracionMeses(prestamosDTO.getDuracion());
        prestamos.setEstado(prestamos.estadoInicial);
        prestamos.setInteres(prestamos.interesInicial);
        prestamos.setFechaCreacion(LocalDateTime.now());

        return prestamos;
    }

    public void aprobacionPrestamo(AprobacionDTO aprobacionDTO){

        estado = aprobacionDTO.getEstado();
        fechaActualizacion = LocalDateTime.now();

    }
    public BigDecimal simulacionCuotas(BigDecimal tasaMensual, Integer duracionMeses, ConsultaDTO consultaDTO){
        BigDecimal tasaMensualDec = tasaMensual.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);
        BigDecimal divisor = BigDecimal.ONE.subtract(BigDecimal.ONE.divide(
                tasaMensualDec.add(BigDecimal.ONE).pow(duracionMeses), 10, RoundingMode.HALF_UP));

        return monto.multiply(tasaMensualDec).divide(divisor, 2, RoundingMode.HALF_UP);
    }
}
