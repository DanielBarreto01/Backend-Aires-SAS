package edu.uptc.PizonAcevedo.service.clientService;

import edu.uptc.PizonAcevedo.domain.model.clientModel.ClientEntity;
import edu.uptc.PizonAcevedo.domain.repository.clientRepository.ClientRepository;
import edu.uptc.PizonAcevedo.service.equipmentService.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentService.class);

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    EquipmentService equipmentService;

    public ClientEntity saveClient(int idEquipment, ClientEntity clientEntity, List<Integer> idsEquipments){
        ClientEntity client = clientRepository.save(clientEntity);
        try {
            idsEquipments.forEach(id -> {equipmentService.updateClientId(id, client.getId());});
            return client;
        } catch (Exception e) {
            logger.error("Error al crear el cleinte: {}", e.getMessage(), e);
            return null;
        }

    }

    public List<ClientEntity> getClients(){
        try {
            return clientRepository.findAll();
        } catch (Exception e) {
            logger.error("Error al obtener los clientes: {}", e.getMessage(), e);
            return null;
        }
    }

    public ClientEntity updateClient(Integer clientId, Map<String, Object> requestData){
        ClientEntity client = clientRepository.findById(clientId)
               .orElse(null);
        List <Integer> idsEquipments = null;
        if(client != null) {
            client.setName((String) requestData.get("name"));
            client.setTypeIdentification((String) requestData.get("typeIdentification"));
            client.setNumberIdentification((Integer) requestData.get("numberIdentification"));
            client.setPhoneNumber(Long.parseLong(requestData.get("phoneNumber").toString()));
            client.setEmail((String) requestData.get("email"));
            client.setAddress((String) requestData.get("address"));
            client.setPathImage((String) requestData.get("pathImage"));
            clientRepository.save(client);
            idsEquipments = (((List<Integer>) requestData.get("idsEquipments")).stream().map(Integer::valueOf).collect(Collectors.toList()));
        }
        try {
            equipmentService.linkEquipmentToClient(clientId, idsEquipments);
            return client;
        } catch (Exception e) {
            logger.error("Error al actualizar el cliente: {}", e.getMessage(), e);
            return null;
        }
    }

//    public boolean updateClient(Integer clientId, ClientEntity clientData, List<Integer> idsEquipments){
//        ClientEntity client = clientRepository.findById(clientId)
//                .orElseThrow(() -> new RuntimeException("Client not found for this id :: " + clientId));
//        client.setName(clientData.getName());
//        client.setTypeIdentification(clientData.getTypeIdentification());
//        client.setNumberIdentification(clientData.getNumberIdentification());
//        client.setPhoneNumber(clientData.getPhoneNumber());
//        client.setEmail(clientData.getEmail());
//        client.setAddress(clientData.getAddress());
//        client.setPathImage(clientData.getPathImage());
//        clientRepository.save(client);
//        try {
//           // clientRepository.updateClient(clientId, clientData.getName(), clientData.getTypeIdentification(), clientData.getNumberIdentification(), clientData.getPhoneNumber(), clientData.getEmail(), clientData.getAddress(), clientData.getPathImage());
//            //equipmentService.linkEquipmentToClient(clientId, idsEquipments);
//
//            equipmentService.linkEquipmentToClient(clientId, idsEquipments);
//            System.out.println(clientId + "        clientid");
//            System.out.println(idsEquipments);
//            return true;
//        } catch (Exception e) {
//            logger.error("Error al actualizar el cliente: {}", e.getMessage(), e);
//            return false;
//        }
//    }
//            clientRepository.updateClient(clientId, clientData.getName(), clientData.getTypeIdentification(), clientData.getNumberIdentification(), clientData.getPhoneNumber(), clientData.getEmail(), clientData.getAddress(), clientData.getPathImage());
//            equipmentService.unlinkEquipmentNotInList(clientId, idsEquipments);
//            equipmentService.linkEquipmentToClient(clientId, idsEquipments);
//        } catch (Exception e) {
//            logger.error("Error al actualizar el cliente: {}", e.getMessage(), e);
//        }
//    }

}
