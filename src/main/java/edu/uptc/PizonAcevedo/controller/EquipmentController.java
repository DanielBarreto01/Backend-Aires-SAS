package edu.uptc.PizonAcevedo.controller;

import edu.uptc.PizonAcevedo.domain.model.equipmentModel.EquipmentEntity;
import edu.uptc.PizonAcevedo.service.equipmentService.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/equipments")
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/create/{userId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createEquipment(@PathVariable int userId, @RequestBody EquipmentEntity equipmentEntity) { //@RequestBody Map<String, Object> requestData
        try {
            //        EquipmentEntity equipmentEntity = EquipmentEntity.builder()
//                .name((String) requestData.get("name"))
//                .equipmentType(EnumEquipment.valueOf((String) requestData.get("equipmentType")))
//                .serialNumber(Integer.parseInt(requestData.get("serialNumber").toString()))
//                .brand((String) requestData.get("brand"))
//                .modelNumber(Integer.parseInt(requestData.get("modelNumber").toString()))
//                .iventoryNumber(Integer.parseInt(requestData.get("iventoryNumber").toString()))
//                .pathImage((String) requestData.get("pathImage"))
//                .build();
            EquipmentEntity newEquipmentEntity = equipmentService.createEquipmentWithMaintenanceRequest(userId, equipmentEntity);

            if (newEquipmentEntity != null) {
                return ResponseEntity.ok("Registro de equipo exitoso");
            }
            return ResponseEntity.badRequest().body("No se pudo registrar el equipo, usuario no valido");
        } catch (DataIntegrityViolationException e) {
            String errorMessage = e.getRootCause().getMessage();
            if (errorMessage.contains("serial_number")) {
                return new ResponseEntity<>("El número de serie ya está registrado.", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.internalServerError().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/getEquipments", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getEquipments() {
        List<EquipmentEntity> equipments = equipmentService.getEquipments();
        if (equipments != null) {
            return ResponseEntity.ok(equipments);
        }
        return ResponseEntity.badRequest().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/getEquipmentsAvailable", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getEquipmentsAvailable(){
        List<EquipmentEntity> equipments = equipmentService.getEquipmentsAvailable();
        if(equipments != null){
            return ResponseEntity.ok(equipments);
        }
        return ResponseEntity.badRequest().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/update/{equipmentId}", method = RequestMethod.PATCH, produces = "application/json")
    public ResponseEntity updateClient(@PathVariable int equipmentId, @RequestBody Map<String, Object> requestData) {
        try {
            EquipmentEntity clientData = equipmentService.updateEquipment(equipmentId, requestData);
            if (clientData != null){
                return ResponseEntity.ok("El Equipo se actualizó exitosamente");
            }
            return new ResponseEntity<>("No se encontro el equipo ha actualizar.", HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = e.getRootCause().getMessage();
            if (errorMessage.contains("serial_number")) {
                return new ResponseEntity<>("El número de serie ya está registrado.", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.internalServerError().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/getEquipmentsByIdClient/{clientId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getEquipmequipmetsByIdClient(@PathVariable int clientId){
        List<EquipmentEntity> equipments = equipmentService.getEquipmentsByClientId(clientId);
        if(equipments != null){
            return ResponseEntity.ok(equipments);
        }
        return ResponseEntity.badRequest().build();
    }

}
