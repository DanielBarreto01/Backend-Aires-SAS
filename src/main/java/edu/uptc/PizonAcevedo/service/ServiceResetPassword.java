package edu.uptc.PizonAcevedo.service;

import edu.uptc.PizonAcevedo.domain.model.UserEntity;
import edu.uptc.PizonAcevedo.domain.repository.CredentialRepository;
import edu.uptc.PizonAcevedo.domain.repository.UserRepository;
import edu.uptc.PizonAcevedo.service.email.IEmailService;
import edu.uptc.PizonAcevedo.domain.model.PasswordResets;
import edu.uptc.PizonAcevedo.util.EmailResetPassword;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import edu.uptc.PizonAcevedo.domain.repository.RepositoryResetPassword;

import java.util.*;

@Service
public class ServiceResetPassword {
    @Autowired
    UserRepository userRepo;

    @Autowired
    IEmailService emailService;
    @Autowired
    RepositoryResetPassword repostoryResetPassword;
    @Autowired
    CredentialRepository credentialRepository;

    public void generateResetPassword(String email) throws MessagingException {
        System.out.println(email);
        UserEntity user = userRepo.findByEmail(email);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Random random = new Random();
        if(user != null){
            String tokenResetPassword = user.getId()+ String.valueOf(UUID.randomUUID());
            int codeResetPassword = 100000 + random.nextInt(900000);
            emailService.sendEmail(user.getEmail(), EmailResetPassword.emailSubject(),
                    EmailResetPassword.bodyEmail(user.getName(), user.getLastName(),String.valueOf(codeResetPassword), tokenResetPassword, credentialRepository.findUserNameByUserId(user.getId())));
            repostoryResetPassword.save(PasswordResets.builder()
                            .resetCode(passwordEncoder.encode(String.valueOf(codeResetPassword)))
                            .createDate(new Date(System.currentTimeMillis()))
                            .expirationDate(new Date(System.currentTimeMillis() +3600000))
                            .token(tokenResetPassword)
                            .user(user).build());
        }else {
            throw new RuntimeException();
        }

    }


    public Map validateStatusToken(String token) {
        PasswordResets passwordResets = repostoryResetPassword.findByStatusAndToken(true, token);
        System.out.println(passwordResets);
        System.out.println(passwordResets.isStatus());
        System.out.println(passwordResets.getExpirationDate().after(new Date()));
        if(passwordResets.isStatus() && passwordResets.getExpirationDate().after(new Date())){
            System.out.println(passwordResets.getExpirationDate());
            return new HashMap() {{
                put("date", passwordResets.getExpirationDate().getTime());
                put("token", token);
            }};
        }
        return null;
    }

    public boolean changePasswordService(String token, String password, String verificationCode) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        PasswordResets passwordResets = repostoryResetPassword.findByStatusAndToken(true, token);
        System.out.println(passwordEncoder.matches(verificationCode, passwordResets.getResetCode()) + "   copara codigos");
        if (passwordResets.isStatus() && passwordEncoder.matches(verificationCode, passwordResets.getResetCode())) {
            repostoryResetPassword.deactivateResetStatus(token);
            credentialRepository.updatePasswordByUserId(password, passwordResets.getUser().getId());
            return true;
        }
        return false;
    }
}
