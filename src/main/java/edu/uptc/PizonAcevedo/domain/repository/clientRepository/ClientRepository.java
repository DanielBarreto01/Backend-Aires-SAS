package edu.uptc.PizonAcevedo.domain.repository.clientRepository;

import edu.uptc.PizonAcevedo.domain.model.clientModel.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
}
