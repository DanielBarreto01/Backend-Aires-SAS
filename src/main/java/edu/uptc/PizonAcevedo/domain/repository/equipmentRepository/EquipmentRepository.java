package edu.uptc.PizonAcevedo.domain.repository.equipmentRepository;

import edu.uptc.PizonAcevedo.domain.model.equipmentModel.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<EquipmentEntity, Integer>{

    List<EquipmentEntity> findByClientIsNull();

    @Modifying
    @Transactional
    @Query("UPDATE EquipmentEntity e SET e.client.id = :clientId WHERE e.id = :equipmentId")
    void updateClientIdByEquipmentId(@Param("equipmentId") Integer equipmentId, @Param("clientId") Integer clientId);


    @Modifying
    @Transactional
    @Query("UPDATE EquipmentEntity e SET e.client = null WHERE e.client.id = :clientId AND e.id NOT IN :equipmentIds")
    void unlinkEquipmentNotInList(Integer clientId, List<Integer> equipmentIds);


    @Modifying
    @Transactional
    @Query("UPDATE EquipmentEntity e SET e.client.id = :clientId WHERE e.id IN :equipmentIds")
    void linkEquipmentToClient(Integer clientId, List<Integer> equipmentIds);

    List<EquipmentEntity> findByClientId(Integer clientId);
    List<EquipmentEntity> findByClientIdAndEquipmentStateTrue(Integer clientId);

    List<EquipmentEntity> findAllByIdIn(List<Integer> equipmentIds);

}
