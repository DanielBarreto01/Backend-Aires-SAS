package edu.uptc.PizonAcevedo.domain.repository.equipmentRepository;

import edu.uptc.PizonAcevedo.domain.model.equipmentModel.UserEquipmentRequestEnti;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEquipmentRepository extends JpaRepository<UserEquipmentRequestEnti, Integer> {
}
