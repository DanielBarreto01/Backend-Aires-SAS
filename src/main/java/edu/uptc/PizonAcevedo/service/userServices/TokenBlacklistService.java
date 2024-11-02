package edu.uptc.PizonAcevedo.service.userServices;

import edu.uptc.PizonAcevedo.domain.model.userModel.BlacklistedToken;
import edu.uptc.PizonAcevedo.domain.repository.repositoryUser.BlacklistedTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenBlacklistService {
    @Autowired
    private BlacklistedTokenRepository blacklistedTokenRepository;

    public void addToBlacklist(String token) {
        BlacklistedToken blacklistedToken = new BlacklistedToken();
        blacklistedToken.setToken(token);
        blacklistedTokenRepository.save(blacklistedToken);
    }

    public boolean isBlacklisted(String token) {
        return blacklistedTokenRepository.existsByToken(token);
    }
}