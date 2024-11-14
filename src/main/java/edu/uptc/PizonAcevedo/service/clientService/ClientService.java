package edu.uptc.PizonAcevedo.service.clientService;

import edu.uptc.PizonAcevedo.domain.model.clientModel.ClientEntity;
import edu.uptc.PizonAcevedo.domain.model.clientModel.JuridicalPersons;
import edu.uptc.PizonAcevedo.domain.model.clientModel.NaturalPerson;
import edu.uptc.PizonAcevedo.domain.repository.clientRepository.ClientRepository;
import edu.uptc.PizonAcevedo.domain.repository.clientRepository.JuridicalPersonsRepository;
import edu.uptc.PizonAcevedo.domain.repository.clientRepository.NaturalPersonRepository;
import edu.uptc.PizonAcevedo.service.equipmentService.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

@Service
public class ClientService {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentService.class);

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    NaturalPersonRepository naturalPersonRepository;

    @Autowired
    JuridicalPersonsRepository juridicalPersonsRepository;

    @Autowired
    EquipmentService equipmentService;

    public NaturalPerson saveClientNatural(NaturalPerson naturalPerson, List<Integer> idsEquipments) {
        NaturalPerson client = naturalPersonRepository.save(naturalPerson);
        try {
            idsEquipments.forEach(id -> {
                equipmentService.updateClientId(id, client.getId());
            });
            return client;
        } catch (Exception e) {
            logger.error("Error al crear el cleinte: {}", e.getMessage(), e);
            return null;
        }

    }


    public JuridicalPersons saveClientJuridical(JuridicalPersons juridicalPersons, List<Integer> idsEquipments){
        JuridicalPersons client =  juridicalPersonsRepository.save(juridicalPersons);
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

    public ClientEntity updateClientJuridical(Integer clientId, Map<String, Object> requestData) {
        ClientEntity client = clientRepository.findById(clientId)
                .orElse(null);
        List <Integer> idsEquipments = null;
        if (client != null) {
            if (client instanceof JuridicalPersons) {
                JuridicalPersons juridicalClient = (JuridicalPersons) client;
                juridicalClient.setNameCompany((String) requestData.get("nameCompany"));
                juridicalClient.setNumberIdentificationCompany((String) requestData.get("numberIdentificationCompany"));
                juridicalClient.setSocialReason((String) requestData.get("socialReason"));
                juridicalClient.setNameLegalRepresentative((String) requestData.get("nameLegalRepresentative"));
                juridicalClient.setPhoneNumberLegalRepresentative(Long.parseLong(requestData.get("phoneNumberLegalRepresentative").toString()));
                juridicalClient.setEmailLegalRepresentative((String) requestData.get("emailLegalRepresentative"));
                juridicalClient.setPhoneNumber(Long.parseLong(requestData.get("phoneNumber").toString()));
                juridicalClient.setEmail((String) requestData.get("email"));
                juridicalClient.setAddress((String) requestData.get("address"));
                juridicalClient.setPathImage((String) requestData.get("pathImage"));
                clientRepository.save(juridicalClient);
            }
            idsEquipments = (((List<Integer>) requestData.get("idsEquipments")).stream().map(Integer::valueOf).collect(java.util.stream.Collectors.toList()));
        }
        try {
            equipmentService.linkEquipmentToClient(clientId, idsEquipments);
        } catch (Exception e) {
            logger.error("Error al actualizar el cliente: {}", e.getMessage(), e);
        }
        return client;
    }

    public ClientEntity updateClientNatural(Integer clientId, Map<String, Object> requestData) {
        ClientEntity client = clientRepository.findById(clientId)
                .orElse(null);
        List <Integer> idsEquipments = null;
        if (client != null) {
            if (client instanceof NaturalPerson) {
                NaturalPerson naturalClient = (NaturalPerson) client;
                naturalClient.setName((String) requestData.get("name"));
                naturalClient.setLastName((String) requestData.get("lastName"));
                naturalClient.setTypeIdentification((String) requestData.get("typeIdentification"));
                naturalClient.setNumberIdentification(Long.parseLong(requestData.get("numberIdentification").toString()));
                naturalClient.setPhoneNumber(Long.parseLong(requestData.get("phoneNumber").toString()));
                naturalClient.setEmail((String) requestData.get("email"));
                naturalClient.setAddress((String) requestData.get("address"));
                naturalClient.setPathImage((String) requestData.get("pathImage"));
                clientRepository.save(naturalClient);
            }
            idsEquipments = (((List<Integer>) requestData.get("idsEquipments")).stream().map(Integer::valueOf).collect(java.util.stream.Collectors.toList()));
        }
        try {
            equipmentService.linkEquipmentToClient(clientId, idsEquipments);
        } catch (Exception e) {
            logger.error("Error al actualizar el cliente: {}", e.getMessage(), e);
        }
        return client;
    }
}
