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
    @RequestMapping(value = "/create/natural-person", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createClientNatural(@RequestBody Map<String, Object> requestData) {
        try {
            NaturalPerson naturalPerson = NaturalPerson.builder()
                    .name((String) requestData.get("name"))
                    .lastName((String) requestData.get("lastName"))
                    .typeIdentification((String) requestData.get("typeIdentification"))
                    .numberIdentification(Long.parseLong( requestData.get("numberIdentification").toString()))
                    .phoneNumber(Long.parseLong(requestData.get("phoneNumber").toString()))
                    .email((String) requestData.get("email"))
                    .address((String) requestData.get("address"))
                    .pathImage((String) requestData.get("pathImage"))
                    .build();
            return ResponseEntity.ok(clientService.saveClientNatural(naturalPerson, ((List<Integer>) requestData.get("idsEquipments")).stream().map(Integer::valueOf).collect(Collectors.toList())));
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

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/create/Juridical-person", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createClientJuridical(@RequestBody Map<String, Object> requestData) {
        try {
            JuridicalPersons naturalPerson = JuridicalPersons.builder()
                    .nameCompany((String) requestData.get("nameCompany"))
                    .numberIdentificationCompany((String) requestData.get("numberIdentificationCompany"))
                    .socialReason((String) requestData.get("socialReason"))
                    .nameLegalRepresentative((String) requestData.get("nameLegalRepresentative"))
                    .phoneNumberLegalRepresentative(Long.parseLong(requestData.get("phoneNumberLegalRepresentative").toString()))
                    .emailLegalRepresentative((String) requestData.get("emailLegalRepresentative"))
                    .phoneNumber(Long.parseLong(requestData.get("phoneNumber").toString()))
                    .email((String) requestData.get("email"))
                    .address((String) requestData.get("address"))
                    .pathImage((String) requestData.get("pathImage"))
                    .build();
            return ResponseEntity.ok(clientService.saveClientJuridical(naturalPerson, ((List<Integer>) requestData.get("idsEquipments")).stream().map(Integer::valueOf).collect(Collectors.toList())));
        } catch (DataIntegrityViolationException e) {
            String errorMessage = e.getRootCause().getMessage();
            if (errorMessage.contains("email")) {
                return new ResponseEntity<>("El correo electrónico ya está registrado.", HttpStatus.NOT_FOUND);
            } else if (errorMessage.contains("number_identification_company")) {
                return new ResponseEntity<>("Este número de identificación ya está registrado.", HttpStatus.NOT_FOUND);
            } else if (errorMessage.contains("social_reason")) {
                return new ResponseEntity<>("La razon social ya está registrada.", HttpStatus.NOT_FOUND);
            } else if(errorMessage.contains("name_company")){
                return new ResponseEntity<>("El nombre de la empresa ya está registrado.", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.internalServerError().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/getClients", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getClients() {
        return ResponseEntity.ok(clientService.getClients());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/update/{clientId}", method = RequestMethod.PATCH, produces = "application/json")
    public ResponseEntity updateClient(@PathVariable int clientId, @RequestBody Map<String, Object> requestData) {
        ClientEntity client = null;
        try {
            if(((String) requestData.get("clientType")).equals("JuridicalPersons"))
                client = clientService.updateClientJuridical(clientId, requestData);
            else client = clientService.updateClientNatural(clientId, requestData);
            if(client != null){
                return ResponseEntity.ok("Cliente actualizado correctamente.");
            }
            return new ResponseEntity<>("No se encontro el cliente.", HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = e.getRootCause().getMessage();
            if (errorMessage.contains("email")) {
                return new ResponseEntity<>("El correo electrónico ya está registrado.", HttpStatus.NOT_FOUND);
            } else if (errorMessage.contains("number_identification_company")) {
                return new ResponseEntity<>("Este número de identificación de la empresa ya está registrado.", HttpStatus.NOT_FOUND);
            } else if (errorMessage.contains("number_identification")) {
                return new ResponseEntity<>("Este número de identificación ya está registrado.", HttpStatus.NOT_FOUND);
            } else if (errorMessage.contains("social_reason")) {
                return new ResponseEntity<>("La razón social ya está registrada.", HttpStatus.NOT_FOUND);
            } else if (errorMessage.contains("name_company")) {
                return new ResponseEntity<>("El nombre de la empresa ya está registrado.", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.internalServerError().build();
        }
    }
}
