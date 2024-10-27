package edu.uptc.PizonAcevedo.domain.repository;

import edu.uptc.PizonAcevedo.domain.model.PasswordResets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RepositoryResetPassword extends JpaRepository<PasswordResets, Integer>{
    PasswordResets findByStatusAndToken(boolean status, String token);

    @Modifying
    @Transactional
    @Query("UPDATE PasswordResets p SET p.status = false WHERE p.token = :token")
    void deactivateResetStatus(String token);
}
