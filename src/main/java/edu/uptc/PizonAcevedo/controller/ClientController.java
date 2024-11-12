package edu.uptc.PizonAcevedo.controller;

import edu.uptc.PizonAcevedo.domain.model.clientModel.ClientEntity;
import edu.uptc.PizonAcevedo.domain.model.clientModel.JuridicalPersons;
import edu.uptc.PizonAcevedo.domain.model.clientModel.NaturalPerson;
import edu.uptc.PizonAcevedo.service.clientService.ClientService;
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
import java.util.stream.Collectors;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/create/natural-person/{equipmentId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createClientNatural(@PathVariable int equipmentId, @RequestBody Map<String, Object> requestData) {
        try {
            NaturalPerson naturalPerson = NaturalPerson.builder()
                    .name((String) requestData.get("name"))
                    .lastName((String) requestData.get("lastName"))
                    .typeIdentification((String) requestData.get("typeIdentification"))
                    .numberIdentification((Integer) requestData.get("numberIdentification"))
                    .phoneNumber(Long.parseLong(requestData.get("phoneNumber").toString()))
                    .email((String) requestData.get("email"))
                    .address((String) requestData.get("address"))
                    .pathImage((String) requestData.get("pathImage"))
                    .build();
            return ResponseEntity.ok(clientService.saveClient(equipmentId, naturalPerson, ((List<Integer>) requestData.get("idsEquipments")).stream().map(Integer::valueOf).collect(Collectors.toList())));
        } catch (DataIntegrityViolationException e) {
            String errorMessage = e.getRootCause().getMessage();
            if (errorMessage.contains("email")) {
                return new ResponseEntity<>("El correo electrónico ya está registrado.", HttpStatus.NOT_FOUND);
            } else if (errorMessage.contains("number_identification")) {
                return new ResponseEntity<>("Este número de identificación ya está registrado.", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.internalServerError().build();
        }
//        try {
//            ClientEntity clientEntity = ClientEntity.builder()
//                    .name((String) requestData.get("name"))
//                    .typeIdentification((String) requestData.get("typeIdentification"))
//                    .numberIdentification((Integer) requestData.get("numberIdentification"))
//                    .phoneNumber(Long.parseLong(requestData.get("phoneNumber").toString()))
//                    .email((String) requestData.get("email"))
//                    .address((String) requestData.get("address"))
//                    .pathImage((String) requestData.get("pathImage"))
//                    .build();
//            ClientEntity client = clientService.saveClient(equipmentId, clientEntity, ((List<Integer>) requestData.get("idsEquipments")).stream().map(Integer::valueOf).collect(Collectors.toList()));
//            if (client != null) {
//                return ResponseEntity.ok("El cliente " + client.getName() + " se creo exitosamente");
//            }
//            return new ResponseEntity<>("No se puedo crear el cliente.", HttpStatus.NOT_FOUND);
//        } catch (DataIntegrityViolationException e) {
//            String errorMessage = e.getRootCause().getMessage();
//            if (errorMessage.contains("email")) {
//                return new ResponseEntity<>("El correo electrónico ya está registrado.", HttpStatus.NOT_FOUND);
//            } else if (errorMessage.contains("number_identification")) {
//                return new ResponseEntity<>("Este número de identificación ya está registrado.", HttpStatus.NOT_FOUND);
//            }
//            return ResponseEntity.internalServerError().build();
//        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/create/Juridical-person/{equipmentId}", method = RequestMethod.POST, produces = "application/json")
    public void createClientJuridical(@PathVariable int equipmentId, @RequestBody Map<String, Object> requestData) {
//        try {
//            JuridicalPersons naturalPerson = JuridicalPersons.builder()
//                    .nameCompany((String) requestData.get("nameCompany"))
//                    .lastName((String) requestData.get("lastName"))
//                    .typeIdentification((String) requestData.get("typeIdentification"))
//                    .numberIdentification((Integer) requestData.get("numberIdentification"))
//                    .phoneNumber(Long.parseLong(requestData.get("phoneNumber").toString()))
//                    .email((String) requestData.get("email"))
//                    .address((String) requestData.get("address"))
//                    .pathImage((String) requestData.get("pathImage"))
//                    .build();
//            return ResponseEntity.ok(clientService.saveClient(equipmentId, naturalPerson, ((List<Integer>) requestData.get("idsEquipments")).stream().map(Integer::valueOf).collect(Collectors.toList())));
//        } catch (DataIntegrityViolationException e) {
//            String errorMessage = e.getRootCause().getMessage();
//            if (errorMessage.contains("email")) {
//                return new ResponseEntity<>("El correo electrónico ya está registrado.", HttpStatus.NOT_FOUND);
//            } else if (errorMessage.contains("number_identification")) {
//                return new ResponseEntity<>("Este número de identificación ya está registrado.", HttpStatus.NOT_FOUND);
//            }
//            return ResponseEntity.internalServerError().build();
//        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/getClients", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getClients() {
        return ResponseEntity.ok(clientService.getClients());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/update/{clientId}", method = RequestMethod.PATCH, produces = "application/json")
    public void updateClient(@PathVariable int clientId, @RequestBody Map<String, Object> requestData) {
//        try {
//            ClientEntity clientData = clientService.updateClient(clientId, requestData);
//            if (clientData != null){
//                return ResponseEntity.ok("El cliente se actualizó exitosamente");
//            }
//            return new ResponseEntity<>("No se encontro el cliente.", HttpStatus.NOT_FOUND);
//        } catch (DataIntegrityViolationException e) {
//            String errorMessage = e.getRootCause().getMessage();
//            if (errorMessage.contains("email")) {
//                return new ResponseEntity<>("El correo electrónico ya está registrado.", HttpStatus.NOT_FOUND);
//            } else if (errorMessage.contains("number_identification")) {
//                return new ResponseEntity<>("Este número de identificación ya está registrado.", HttpStatus.NOT_FOUND);
//            }
//            return ResponseEntity.internalServerError().build();
//        }
    }
}
