package gestionprestamobancario.prestamobancario.model.DTO;

import jakarta.validation.constraints.NotNull;

public class ConsultaDTO {
    @NotNull(message = "El id es obligatorio")
    private Integer idConsulta;

    public ConsultaDTO(){}
    public ConsultaDTO(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }
}
