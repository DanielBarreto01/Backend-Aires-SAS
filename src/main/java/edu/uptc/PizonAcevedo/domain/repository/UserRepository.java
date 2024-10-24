package edu.uptc.PizonAcevedo.domain.repository;

import edu.uptc.PizonAcevedo.domain.model.Credential;
import edu.uptc.PizonAcevedo.domain.model.ERole;
import edu.uptc.PizonAcevedo.domain.model.Roles;
import edu.uptc.PizonAcevedo.domain.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    // @Query("{ 'userId' : ?0 }")
    UserEntity findUserByCredential(Credential credential);
    UserEntity findUserById(int id);

    @Query(
            value = "SELECT ur.role_id FROM user_roles ur WHERE ur.user_id = :userId",
            nativeQuery = true
    )
    List<Integer> findRoleIdByUserId(@Param("userId") int userId);

    @Query("SELECT u FROM UserEntity u JOIN u.roles r WHERE r.name = :role")
    List<UserEntity> findByRole(@Param("role") ERole role);


    @Modifying
    @Query("UPDATE Roles u SET u.name = :name  WHERE u.id = :id")
    void updateRoles(@Param("id") int id, @Param("name") ERole name);


    @Modifying
    @Query("UPDATE UserEntity u SET u.name = :name, u.lastName = :lastName, u.typeIdentification = :typeIdentification, "
            + "u.numberIdentification = :numberIdentification, u.email = :email, u.phoneNumber = :phoneNumber, "
            + "u.address = :address, u.pathImage = :pathImage, u.userStatus = :userStatus WHERE u.id = :id")
    void updateUser(
            @Param("id") int id,
            @Param("name") String name,
            @Param("lastName") String lastName,
            @Param("typeIdentification") String typeIdentification,
            @Param("numberIdentification") String numberIdentification,
            @Param("email") String email,
            @Param("phoneNumber") Long phoneNumber,
            @Param("address") String address,
            @Param("pathImage") String pathImage,
            @Param("userStatus") boolean userStatus);
}

