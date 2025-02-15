package gestionprestamobancario.prestamobancario.model.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public class AprobacionDTO {
    @NotNull(message = "El id prestamo es obligatorio")
    private Integer idPrestamo;
    @NotNull(message = "El estado es obligatorio")
    @Pattern(regexp = "Aprobado|Rechazado", message = "Estado solo puede ser Aprobado o Rechazado")
    private String estado;

    public AprobacionDTO(){}
    public AprobacionDTO(Integer idPrestamo, String estado) {
        this.idPrestamo = idPrestamo;
        this.estado = estado;
    }

    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

