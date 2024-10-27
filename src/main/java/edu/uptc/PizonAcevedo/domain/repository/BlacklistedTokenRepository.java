package edu.uptc.PizonAcevedo.domain.repository;

import edu.uptc.PizonAcevedo.domain.model.BlacklistedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistedTokenRepository extends JpaRepository<BlacklistedToken, Long> {
    boolean existsByToken(String token);


}