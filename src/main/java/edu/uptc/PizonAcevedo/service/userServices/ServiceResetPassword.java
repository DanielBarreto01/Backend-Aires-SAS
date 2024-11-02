package edu.uptc.PizonAcevedo.service.userServices;

import edu.uptc.PizonAcevedo.domain.model.userModel.UserEntity;
import edu.uptc.PizonAcevedo.domain.repository.repositoryUser.CredentialRepository;
import edu.uptc.PizonAcevedo.domain.repository.repositoryUser.UserRepository;
import edu.uptc.PizonAcevedo.service.email.IEmailService;
import edu.uptc.PizonAcevedo.domain.model.userModel.PasswordResets;
import edu.uptc.PizonAcevedo.util.EmailResetPassword;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import edu.uptc.PizonAcevedo.domain.repository.repositoryUser.RepositoryResetPassword;

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

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
        if(token.isBlank() || token.isEmpty()){
            return null;
        }
        PasswordResets passwordResets = repostoryResetPassword.findByStatusAndToken(true, token);
        if(passwordResets.isStatus() && passwordResets.getExpirationDate().after(new Date())){
            System.out.println(passwordResets.getExpirationDate());
            return new HashMap() {{
                put("date", passwordResets.getExpirationDate().getTime());
                put("token", token);
            }};
        }
        return null;
    }

//    public boolean validateCode(String token, String verificationCode) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        PasswordResets passwordResets = repostoryResetPassword.findByStatusAndToken(true, token);
//        if(passwordResets.isStatus() && passwordEncoder.matches(verificationCode, passwordResets.getResetCode())){
//            return true;
//        }
//        return false;
//    }
//
//    public boolean changePasswordService(String token, String password, String verificationCode) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        PasswordResets passwordResets = repostoryResetPassword.findByStatusAndToken(true, token);
//        if (passwordResets.isStatus() && passwordEncoder.matches(verificationCode, passwordResets.getResetCode())) {
//            repostoryResetPassword.deactivateResetStatus(token);
//            credentialRepository.updatePasswordByUserId(password, passwordResets.getUser().getId());
//            return true;
//        }
//        return false;
//    }



    public boolean validateCode(String token, String verificationCode) {
        return isValidReset(token, verificationCode);
    }

    public boolean changePasswordService(String token, String password, String verificationCode) {
        if (isValidReset(token, verificationCode)) {
            credentialRepository.updatePasswordByUserId(password, getUserIdByToken(token));
            repostoryResetPassword.deactivateResetStatus(token);
            return true;
        }
        return false;
    }

    private boolean isValidReset(String token, String verificationCode) {
        PasswordResets reset = repostoryResetPassword.findByStatusAndToken(true, token);
        return reset != null && passwordEncoder.matches(verificationCode, reset.getResetCode());
    }

    private int getUserIdByToken(String token) {
        return repostoryResetPassword.findByStatusAndToken(true, token).getUser().getId();
    }
}
