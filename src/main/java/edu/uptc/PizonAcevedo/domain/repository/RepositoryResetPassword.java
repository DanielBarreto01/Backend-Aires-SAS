package edu.uptc.PizonAcevedo.domain.repository;

import edu.uptc.PizonAcevedo.domain.model.PasswordResets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryResetPassword extends JpaRepository<PasswordResets, Integer>{

}
