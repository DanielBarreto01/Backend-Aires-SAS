package edu.uptc.PizonAcevedo.service.equipmentService;

import edu.uptc.PizonAcevedo.domain.model.equipmentModel.EquipmentEntity;
import edu.uptc.PizonAcevedo.domain.model.equipmentModel.UserEquipmentRequestEnti;
import edu.uptc.PizonAcevedo.domain.model.userModel.UserEntity;
import edu.uptc.PizonAcevedo.domain.repository.equipmentRepository.EquipmentRepository;
import edu.uptc.PizonAcevedo.domain.repository.equipmentRepository.UserEquipmentRepository;
import edu.uptc.PizonAcevedo.service.userServices.UserMgmt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

@Service
public class EquipmentService {
    private static final Logger logger = LoggerFactory.getLogger(EquipmentService.class);


    @Autowired
    EquipmentRepository equipmentRepository;

    @Autowired
    UserEquipmentRepository userEquipmentRepository;

    @Autowired
    UserMgmt userService;


    public EquipmentEntity createEquipmentWithMaintenanceRequest( int userId, EquipmentEntity equipment) {
        // Guardar el equipo
        try {
            UserEntity user = userService.findUserById(userId);
            EquipmentEntity savedEquipment = equipmentRepository.save(equipment);
            UserEquipmentRequestEnti maintenanceRequest = new UserEquipmentRequestEnti();
            maintenanceRequest.setEquipment(savedEquipment);
            maintenanceRequest.setUser(user);
            maintenanceRequest.setState(false);
            maintenanceRequest.setGenerationDate(new Date());

            userEquipmentRepository.save(maintenanceRequest);

            return savedEquipment;

        } catch (Exception e) {
            logger.error("Error al crear el equipo y la solicitud de mantenimiento: {}", e.getMessage(), e);            return null;
        }


    }

    public List<EquipmentEntity> getEquipments(){
        try {
            return equipmentRepository.findAll();
        } catch (Exception e) {
            logger.error("Error al obtener los equipos: {}", e.getMessage(), e);
            return null;
        }
    }

    public boolean updateClientId(Integer equipmentId, Integer clientId){
        try {
            equipmentRepository.updateClientIdByEquipmentId(equipmentId, clientId);
            return true;
        } catch (Exception e) {
            logger.error("Error al actualizar el cliente del equipo: {}", e.getMessage(), e);
            return false;
        }
    }
}
