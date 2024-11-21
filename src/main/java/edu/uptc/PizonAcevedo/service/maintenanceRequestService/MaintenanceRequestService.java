package edu.uptc.PizonAcevedo.service.maintenanceRequestService;

import edu.uptc.PizonAcevedo.domain.model.clientModel.ClientEntity;
import edu.uptc.PizonAcevedo.domain.model.maintenanceRequest.MaintenanceRequestEntity;
import edu.uptc.PizonAcevedo.domain.model.userModel.ERole;
import edu.uptc.PizonAcevedo.domain.model.userModel.UserEntity;
import edu.uptc.PizonAcevedo.domain.repository.clientRepository.ClientRepository;
import edu.uptc.PizonAcevedo.domain.repository.equipmentRepository.EquipmentRepository;
import edu.uptc.PizonAcevedo.domain.repository.maintenanceRequestRepository.MaintenanceRequestRepository;
import edu.uptc.PizonAcevedo.domain.repository.repositoryUser.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class MaintenanceRequestService {

    @Autowired
    MaintenanceRequestRepository maintenanceRequestRepository;

    @Autowired
    EquipmentRepository equipmentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClientRepository clientRepository;

    @Transactional
    public MaintenanceRequestEntity createMaintenanceRequest(Map<String, Object> requestData) throws Exception {
        UserEntity technician = userRepository.findById((Integer) requestData.get("technician")).orElse(null);
        ClientEntity client = clientRepository.findById((Integer) requestData.get("client")).orElse(null);

        List IdsEquipments = (((List<Integer>) requestData.get("equipments")).stream().map(Integer::valueOf).collect(java.util.stream.Collectors.toList()));
        List equipments = equipmentRepository.findAllByIdIn(IdsEquipments);
        if (technician == null) {
            throw new Exception("No se encontro el usuario tecnico");
        }
        boolean isAdmin = technician.getRoles().stream()
                .anyMatch(role -> role.getName() == ERole.ADMIN);

        if(technician != null  &&  client != null && equipments.size() > 0 && !isAdmin){
            MaintenanceRequestEntity maintenanceRequest =  maintenanceRequestRepository.save(MaintenanceRequestEntity.builder()
                    .client(client)
                    .technician(technician)
                    .equipments(new HashSet<>(equipments))
                    .requestDate(new Date())
                    .requestNumber(generateUniqueRequestNumber())
                    .build());
            return maintenanceRequest;
        }
        throw new Exception("El técnico no existe o el usrio no es tecnico");
    }

    private String generateUniqueRequestNumber() {
        String prefix = "SLM-";
        LocalDate currentDate = LocalDate.now();
        String datePart = currentDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int countForDay = maintenanceRequestRepository.countByDate(currentDate);
        String requestNumber = prefix + datePart + "-" + String.format("%04d", countForDay + 1);
        while (maintenanceRequestRepository.existsByRequestNumber(requestNumber)) {
            countForDay++;
            requestNumber = prefix + datePart + "-" + String.format("%04d", countForDay);
        }
        return requestNumber;
    }

    public List<MaintenanceRequestEntity> getMaintenanceRequests() throws Exception {
        return maintenanceRequestRepository.findAll();
    }
}
