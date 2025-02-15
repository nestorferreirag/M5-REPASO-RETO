package gestionprestamobancario.prestamobancario.model.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class PrestamosDTO {
    @NotNull(message = "El Id cliente es obligatorio")
    private Integer idCliente;
    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "Solo se permiten valores positivos")
    private BigDecimal monto;
    @NotNull(message = "La duracion es obligatoria")
    @Positive(message = "Solo se permiten valores positivos")
    private Integer duracion;

    public PrestamosDTO(){}

    public PrestamosDTO(Integer idCliente, BigDecimal monto, Integer duracion) {
        this.idCliente = idCliente;
        this.monto = monto;
        this.duracion = duracion;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
}
