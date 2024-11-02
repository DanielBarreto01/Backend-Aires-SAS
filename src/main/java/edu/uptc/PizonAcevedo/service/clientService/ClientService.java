package edu.uptc.PizonAcevedo.service.clientService;

import edu.uptc.PizonAcevedo.domain.model.clientModel.ClientEntity;
import edu.uptc.PizonAcevedo.domain.repository.clientRepository.ClientRepository;
import edu.uptc.PizonAcevedo.service.equipmentService.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class ClientService {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentService.class);

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    EquipmentService equipmentService;

    public ClientEntity saveClient(int idEquipment, ClientEntity clientEntity){
        try {
            ClientEntity client = clientRepository.save(clientEntity);
            equipmentService.updateClientId(idEquipment, client.getId());

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

}
