package edu.uptc.PizonAcevedo.domain.repository.repositoryUser;

import edu.uptc.PizonAcevedo.domain.model.userModel.BlacklistedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistedTokenRepository extends JpaRepository<BlacklistedToken, Long> {
    boolean existsByToken(String token);


}