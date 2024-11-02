package edu.uptc.PizonAcevedo.domain.repository.repositoryUser;

import edu.uptc.PizonAcevedo.domain.model.userModel.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credential, Integer> {
    Optional<Credential> findByUserName(String userName);
    boolean existsByUserName(String userName);


    @Modifying
    @Transactional
    @Query("UPDATE Credential c SET c.password = :newPassword WHERE c.user.id = :userId")
    int updatePasswordByUserId(String newPassword, Integer userId);

    @Query("SELECT c.userName FROM Credential c WHERE c.user.id = :userId")
    String findUserNameByUserId(Integer userId);
}
