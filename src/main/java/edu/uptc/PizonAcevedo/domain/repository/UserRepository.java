package edu.uptc.PizonAcevedo.domain.repository;

import edu.uptc.PizonAcevedo.domain.model.Credential;
import edu.uptc.PizonAcevedo.domain.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    // @Query("{ 'userId' : ?0 }")
    UserEntity findUserByCredential(Credential credential);
}
