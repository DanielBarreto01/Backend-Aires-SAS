package edu.uptc.PizonAcevedo.controller;

import edu.uptc.PizonAcevedo.domain.model.clientModel.ClientEntity;
import edu.uptc.PizonAcevedo.service.clientService.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @RequestMapping(value = "/create/{equipmentId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createClient(@PathVariable int equipmentId, @RequestBody ClientEntity clientEntity){
        ClientEntity client = clientService.saveClient(equipmentId, clientEntity);
        if (client != null){
            return ResponseEntity.ok(client);
        }
        return ResponseEntity.badRequest().build();
    }


    @RequestMapping(value = "/getClients", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getClients(){
        return ResponseEntity.ok(clientService.getClients());
    }
}
