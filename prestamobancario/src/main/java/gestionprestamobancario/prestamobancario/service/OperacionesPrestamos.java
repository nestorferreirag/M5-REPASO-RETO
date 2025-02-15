package gestionprestamobancario.prestamobancario.service;

import gestionprestamobancario.prestamobancario.model.Clientes;
import gestionprestamobancario.prestamobancario.model.DTO.AprobacionDTO;
import gestionprestamobancario.prestamobancario.model.DTO.ConsultaDTO;
import gestionprestamobancario.prestamobancario.model.DTO.PrestamosDTO;
import gestionprestamobancario.prestamobancario.model.Prestamos;
import gestionprestamobancario.prestamobancario.repository.ClientesRepository;
import gestionprestamobancario.prestamobancario.repository.PrestamosRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OperacionesPrestamos {
    private final ClientesRepository clientesRepository;
    private final PrestamosRepository prestamosRepository;

    public OperacionesPrestamos(ClientesRepository clientesRepository, PrestamosRepository prestamosRepository) {
        this.clientesRepository = clientesRepository;
        this.prestamosRepository = prestamosRepository;
    }

    public void solicitudPrestamo(PrestamosDTO prestamosDTO){

        Clientes cliente = clientesRepository.findById(prestamosDTO.getIdCliente())
                .orElseThrow(() -> new NoSuchElementException("El cliente " + prestamosDTO.getIdCliente() + " no fue encontrado"));

        Prestamos prestamo = Prestamos.solicitudPrestamo(cliente, prestamosDTO);

        prestamosRepository.save(prestamo);

    }
    public void aprobacionPrestamo(AprobacionDTO aprobacionDTO){

        Prestamos prestamos = prestamosRepository.findById(aprobacionDTO.getIdPrestamo())
                .orElse(null);
        if (prestamos != null){
            prestamos.aprobacionPrestamo(aprobacionDTO);
            prestamosRepository.save(prestamos);
        }else{
            throw new NoSuchElementException("El prestamo " + aprobacionDTO.getIdPrestamo() + " no fue encontrado");
        }
    }
    public String consultaEstado(ConsultaDTO consultaDTO){
        Prestamos prestamos = prestamosRepository.findById(consultaDTO.getIdConsulta()).orElse(null);
        if(prestamos != null){
            return prestamos.getEstado();
        }else{
            throw new NoSuchElementException("El prestamo " + consultaDTO.getIdConsulta()+ " no fue encontrado");
        }
    }
    public List<Prestamos> prestamosCliente(Integer id){
        Clientes cliente = clientesRepository.findById(id)
                .orElse(null);
        if(cliente != null){
            return prestamosRepository.findBycliente_ClienteId(id);
        }else{
            throw new NoSuchElementException("El cliente " + id+ " no fue encontrado");
        }
    }
    public BigDecimal simulacionCuotas(ConsultaDTO consultaDTO){
        Prestamos prestamos = prestamosRepository.findById(consultaDTO.getIdConsulta()).orElse(null);
        if(prestamos != null){
            return prestamos.simulacionCuotas(prestamos.getInteres(),prestamos.getDuracionMeses(),consultaDTO);
        }else{
            throw new NoSuchElementException("El prestamo " + consultaDTO.getIdConsulta()+ " no fue encontrado");
        }
    }
    public Prestamos consultarPrestamo(ConsultaDTO consultaDTO){
        Prestamos prestamos = prestamosRepository.findById(consultaDTO.getIdConsulta()).orElse(null);
        if(prestamos != null){
            return prestamos;
        }else{
            throw new NoSuchElementException("El cliente " + consultaDTO.getIdConsulta()+ " no fue encontrado");
        }
    }
    public List<Prestamos> tresPrestamosCliente(Integer id){
        Clientes cliente = clientesRepository.findById(id)
                .orElse(null);
        if(cliente != null){
            return prestamosRepository.findTop3ByCliente_ClienteIdOrderByFechaCreacionDesc(id);
        }else{
            throw new NoSuchElementException("El cliente " + id+ " no fue encontrado");
        }
    }
}
