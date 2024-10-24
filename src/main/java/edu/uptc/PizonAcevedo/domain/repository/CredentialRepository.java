package edu.uptc.PizonAcevedo.domain.repository;

import edu.uptc.PizonAcevedo.domain.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credential, Integer> {
    Optional<Credential> findByUserName(String userName);
    boolean existsByUserName(String userName);
}
