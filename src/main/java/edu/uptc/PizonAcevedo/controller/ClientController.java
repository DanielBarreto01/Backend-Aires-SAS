package edu.uptc.PizonAcevedo.controller;

import edu.uptc.PizonAcevedo.domain.model.clientModel.ClientEntity;
import edu.uptc.PizonAcevedo.service.clientService.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @RequestMapping(value = "/create/{equipmentId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createClient(@PathVariable int equipmentId, @RequestBody Map<String, Object> requestData) {
        try {
            ClientEntity clientEntity = ClientEntity.builder()
                    .name((String) requestData.get("name"))
                    .typeIdentification((String) requestData.get("typeIdentification"))
                    .numberIdentification((Integer) requestData.get("numberIdentification"))
                    .phoneNumber(Long.parseLong(requestData.get("phoneNumber").toString()))
                    .email((String) requestData.get("email"))
                    .address((String) requestData.get("address"))
                    .pathImage((String) requestData.get("pathImage"))
                    .build();
            ClientEntity client = clientService.saveClient(equipmentId, clientEntity, ((List<Integer>) requestData.get("idsEquipments")).stream().map(Integer::valueOf).collect(Collectors.toList()));
            if (client != null) {
                return ResponseEntity.ok("El cliente " + client.getName() + " se creo exitosamente");
            }
            return new ResponseEntity<>("No se puedo crear el cliente.", HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = e.getRootCause().getMessage();
            if (errorMessage.contains("email")) {
                return new ResponseEntity<>("El correo electrónico ya está registrado.", HttpStatus.NOT_FOUND);
            } else if (errorMessage.contains("number_identification")) {
                return new ResponseEntity<>("Este número de identificación ya está registrado.", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/getClients", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getClients() {
        return ResponseEntity.ok(clientService.getClients());
    }

    @RequestMapping(value = "/update/{clientId}", method = RequestMethod.PATCH, produces = "application/json")
    public ResponseEntity updateClient(@PathVariable int clientId, @RequestBody Map<String, Object> requestData) {
        try {
            ClientEntity clientData = clientService.updateClient(clientId, requestData);
            if (clientData != null){
                return ResponseEntity.ok("El cliente se actualizó exitosamente");
            }
            return new ResponseEntity<>("No se encontro el cliente.", HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = e.getRootCause().getMessage();
            if (errorMessage.contains("email")) {
                return new ResponseEntity<>("El correo electrónico ya está registrado.", HttpStatus.NOT_FOUND);
            } else if (errorMessage.contains("number_identification")) {
                return new ResponseEntity<>("Este número de identificación ya está registrado.", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.internalServerError().build();
        }
    }
}
