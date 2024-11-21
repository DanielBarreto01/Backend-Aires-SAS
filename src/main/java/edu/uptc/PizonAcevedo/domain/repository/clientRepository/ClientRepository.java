package edu.uptc.PizonAcevedo.domain.repository.clientRepository;

import edu.uptc.PizonAcevedo.domain.model.clientModel.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
    List<ClientEntity> findByClientStateTrue();
}
