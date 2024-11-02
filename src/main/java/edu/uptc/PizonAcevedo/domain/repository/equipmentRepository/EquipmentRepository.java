package edu.uptc.PizonAcevedo.domain.repository.equipmentRepository;

import edu.uptc.PizonAcevedo.domain.model.equipmentModel.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface EquipmentRepository extends JpaRepository<EquipmentEntity, Integer>{
    @Modifying
    @Transactional
    @Query("UPDATE EquipmentEntity e SET e.client.id = :clientId WHERE e.id = :equipmentId")
    void updateClientIdByEquipmentId(@Param("equipmentId") Integer equipmentId, @Param("clientId") Integer clientId);
}
