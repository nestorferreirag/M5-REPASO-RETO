package gestionprestamobancario.prestamobancario.repository;

import gestionprestamobancario.prestamobancario.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository <Clientes, Integer> {
}
