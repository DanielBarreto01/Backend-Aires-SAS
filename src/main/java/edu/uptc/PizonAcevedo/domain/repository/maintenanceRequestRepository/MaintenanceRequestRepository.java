package edu.uptc.PizonAcevedo.domain.repository.maintenanceRequestRepository;

import edu.uptc.PizonAcevedo.domain.model.maintenanceRequest.MaintenanceRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequestEntity, Integer> {
    // Contar el número de solicitudes creadas en la fecha actual
    @Query("SELECT COUNT(m) FROM MaintenanceRequestEntity m WHERE DATE(m.requestDate) = :date")
    int countByDate(LocalDate date);

    // Verificar si un número de solicitud ya existe
    boolean existsByRequestNumber(String requestNumber);
}
