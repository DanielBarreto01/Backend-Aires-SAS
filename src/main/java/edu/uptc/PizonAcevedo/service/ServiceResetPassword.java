package edu.uptc.PizonAcevedo.service;

import edu.uptc.PizonAcevedo.domain.model.UserEntity;
import edu.uptc.PizonAcevedo.domain.repository.UserRepository;
import edu.uptc.PizonAcevedo.service.email.IEmailService;
import edu.uptc.PizonAcevedo.domain.model.PasswordResets;
import edu.uptc.PizonAcevedo.util.EmailResetPassword;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import edu.uptc.PizonAcevedo.domain.repository.RepositoryResetPassword;

import java.util.Date;
import java.util.Random;

@Service
public class ServiceResetPassword {
    @Autowired
    UserRepository userRepo;

    @Autowired
    IEmailService emailService;
    @Autowired
    RepositoryResetPassword repostoryResetPassword;

    public void generateResetPassword(String email) throws MessagingException {
        UserEntity user = userRepo.findByEmail(email);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Random random = new Random();
        if(user == null){
            int codeResetPassword = 100000 + random.nextInt(900000);
            emailService.sendEmail(user.getEmail(), EmailResetPassword.emailSubject(), EmailResetPassword.bodyEmail(user.getName(), user.getLastName(),String.valueOf(codeResetPassword)));
            repostoryResetPassword.save(PasswordResets.builder()
                            .resetCode(passwordEncoder.encode(String.valueOf(codeResetPassword)))
                            .createDate(new Date(System.currentTimeMillis()))
                            .expirationDate(new Date(System.currentTimeMillis() +3600000)).build());
        }else {
            throw new RuntimeException();
        }

    }


}
