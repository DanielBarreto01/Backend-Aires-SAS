package edu.uptc.PizonAcevedo.service.equipmentService;

import edu.uptc.PizonAcevedo.domain.model.equipmentModel.EnumEquipment;
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
import java.util.Map;

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
        EquipmentEntity savedEquipment = equipmentRepository.save(equipment);
        try {
            UserEntity user = userService.findUserById(userId);
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

    public List<EquipmentEntity> getEquipmentsAvailable(){
        try {
            return equipmentRepository.findByClientIsNull();
        } catch (Exception e) {
            logger.error("Error al obtener los equipos disponibles: {}", e.getMessage(), e);
            return null;
        }
    }

    public EquipmentEntity updateEquipment(int equipmentId, Map<String, Object> requestData) {
        EquipmentEntity equipment = equipmentRepository.findById(equipmentId).orElse(null);
        System.out.println(equipment.getId());
        if(equipment != null) {
            equipment.setName((String) requestData.get("name"));
            equipment.setEquipmentType(EnumEquipment.valueOf((String) requestData.get("equipmentType")));
            equipment.setSerialNumber(Integer.parseInt(requestData.get("serialNumber").toString()));
            equipment.setBrand((String) requestData.get("brand"));
            equipment.setModelNumber(Integer.parseInt(requestData.get("modelNumber").toString()));
            equipment.setIventoryNumber(Integer.parseInt(requestData.get("iventoryNumber").toString()));
            equipment.setPathImage((String) requestData.get("pathImage"));
            equipmentRepository.save(equipment);
        }
        return equipment;
    }

    public boolean linkEquipmentToClient(Integer clientId, List<Integer> equipmentIds){
        try {
            equipmentRepository.unlinkEquipmentNotInList(clientId, equipmentIds);
            equipmentRepository.linkEquipmentToClient(clientId, equipmentIds);
            return true;
        } catch (Exception e) {
            logger.error("Error al vincular los equipos al cliente: {}", e.getMessage(), e);
            return false;
        }
    }
}
