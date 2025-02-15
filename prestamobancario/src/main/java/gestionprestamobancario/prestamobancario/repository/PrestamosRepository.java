package gestionprestamobancario.prestamobancario.repository;

import gestionprestamobancario.prestamobancario.model.Clientes;
import gestionprestamobancario.prestamobancario.model.Prestamos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrestamosRepository extends JpaRepository<Prestamos, Integer> {

    // Obtener los préstamos de un cliente por su ID
      List<Prestamos> findBycliente_ClienteId(Integer id);

      List<Prestamos> findTop3ByCliente_ClienteIdOrderByFechaCreacionDesc(Integer id);

}
