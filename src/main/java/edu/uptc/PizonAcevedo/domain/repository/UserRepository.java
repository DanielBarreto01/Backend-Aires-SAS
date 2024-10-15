package edu.uptc.PizonAcevedo.domain.repository;

import edu.uptc.PizonAcevedo.domain.model.Credential;
import edu.uptc.PizonAcevedo.domain.model.ERole;
import edu.uptc.PizonAcevedo.domain.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    // @Query("{ 'userId' : ?0 }")
    UserEntity findUserByCredential(Credential credential);

    @Query("SELECT u FROM UserEntity u JOIN u.roles r WHERE r.name = :role")
    List<UserEntity> findByRole(@Param("role") ERole role);
}
