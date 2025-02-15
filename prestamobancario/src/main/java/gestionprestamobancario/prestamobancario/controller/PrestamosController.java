package gestionprestamobancario.prestamobancario.controller;

import gestionprestamobancario.prestamobancario.model.DTO.AprobacionDTO;
import gestionprestamobancario.prestamobancario.model.DTO.ConsultaDTO;
import gestionprestamobancario.prestamobancario.model.DTO.PrestamosDTO;
import gestionprestamobancario.prestamobancario.model.Prestamos;
import gestionprestamobancario.prestamobancario.service.OperacionesPrestamos;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamosController {
    private final OperacionesPrestamos operacionesPrestamos;

    public PrestamosController(OperacionesPrestamos operacionesPrestamos) {
        this.operacionesPrestamos = operacionesPrestamos;
    }
    @PostMapping("/solicitudPrestamo")
    public String SolicitudPrestamo(@Valid @RequestBody PrestamosDTO prestamosDTO){
        operacionesPrestamos.solicitudPrestamo(prestamosDTO);
        return "Prestamo fue creado";
    }
    @PostMapping("/aprobacionPrestamo")
    public String AprobacionPrestamo(@Valid @RequestBody AprobacionDTO aprobacionDTO){
        operacionesPrestamos.aprobacionPrestamo(aprobacionDTO);
        return "El prestamo fue " + aprobacionDTO.getEstado();
    }
    @PostMapping("/consultaEstado")
    public String ConsultaEstado(@Valid @RequestBody ConsultaDTO consultaDTO){
        return "El estado del prestamo " + consultaDTO.getIdConsulta() + " es " + operacionesPrestamos.consultaEstado(consultaDTO);
    }

    @PostMapping("/prestamosCliente")
    public ResponseEntity<List<Prestamos>> PrestamosCliente(@Valid @RequestBody ConsultaDTO consultaDTO){
        List<Prestamos> prestamos = operacionesPrestamos.prestamosCliente(consultaDTO.getIdConsulta());
        if(prestamos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(prestamos);
    }
    @PostMapping("/simulacionCuotas")
    public String SimulacionCuotas(@Valid @RequestBody ConsultaDTO consultaDTO){
        return "La cuota mensual es por un valor de $" + operacionesPrestamos.simulacionCuotas(consultaDTO);
    }
    @PostMapping("/consultarPrestamo")
    public Prestamos consultarPrestamo(@Valid @RequestBody ConsultaDTO consultaDTO){
        return operacionesPrestamos.consultarPrestamo(consultaDTO);
    }
    @PostMapping("/tresPrestamosCliente")
    public ResponseEntity<List<Prestamos>> TresPrestamosCliente(@Valid @RequestBody ConsultaDTO consultaDTO){
        List<Prestamos> prestamos = operacionesPrestamos.tresPrestamosCliente(consultaDTO.getIdConsulta());
        if(prestamos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(prestamos);
    }
}
