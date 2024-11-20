package edu.uptc.PizonAcevedo.controller;

import edu.uptc.PizonAcevedo.service.maintenanceRequestService.MaintenanceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping("/maintenanceRequest")
public class MaintenanceRequestController {

    @Autowired
    MaintenanceRequestService maintenanceRequestService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createMaintenanceRequest(@RequestBody Map<String, Object> requestData) {
        try {
            return ResponseEntity.ok(maintenanceRequestService.createMaintenanceRequest(requestData));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
