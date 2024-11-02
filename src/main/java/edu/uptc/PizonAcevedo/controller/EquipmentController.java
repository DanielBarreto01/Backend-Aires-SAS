package edu.uptc.PizonAcevedo.controller;

import edu.uptc.PizonAcevedo.domain.model.equipmentModel.EquipmentEntity;
import edu.uptc.PizonAcevedo.service.equipmentService.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/equipments")
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;


    @RequestMapping(value = "/create/{userId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createEquipment(@PathVariable int userId, @RequestBody EquipmentEntity equipment){

       EquipmentEntity newEquipmentEntity =  equipmentService.createEquipmentWithMaintenanceRequest(userId, equipment);

       if(newEquipmentEntity != null){
              return ResponseEntity.ok(newEquipmentEntity);
         }
       return ResponseEntity.badRequest().build();
    }

    @RequestMapping(value = "/getEquipments", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getEquipments(){
        List<EquipmentEntity> equipments = equipmentService.getEquipments();
        if(equipments != null){
            return ResponseEntity.ok(equipments);
        }
        return ResponseEntity.badRequest().build();
    }
}
