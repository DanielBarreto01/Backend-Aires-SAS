package edu.uptc.PizonAcevedo.service;

import edu.uptc.PizonAcevedo.domain.model.Credential;
import edu.uptc.PizonAcevedo.domain.model.ERole;
import edu.uptc.PizonAcevedo.domain.model.Roles;
import edu.uptc.PizonAcevedo.domain.repository.CredentialRepository;
import edu.uptc.PizonAcevedo.util.Email;
import edu.uptc.PizonAcevedo.domain.repository.UserRepository;
import edu.uptc.PizonAcevedo.domain.model.UserEntity;
import edu.uptc.PizonAcevedo.service.email.IEmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
public class UserMgmt {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-.+!?_@";
    private static final int PASSWORD_LENGTH = 8;

    @Autowired
    UserRepository userRepo;

    @Autowired
    IEmailService emailService;

    @Autowired
    CredentialRepository credentialRepository;

    public void saveUser(UserEntity user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userRepo.save(user);
        Credential credential = Credential.builder()
                    .userName(generateBaseUsername(user))
                    .user(user)
                    .password(generateRandomPassword())
                .build();
        try {
            emailService.sendEmail(user.getEmail(), Email.emailSubject(), Email.bodyEmail(user.getName(), user.getLastName(), credential.getUserName(), credential.getPassword()));
            credential.setPassword(new BCryptPasswordEncoder().encode(credential.getPassword()));
            credentialRepository.save(credential);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<UserEntity> getUsers(){
        return userRepo.findAll();
    }

    public static String generateBaseUsername(UserEntity user) {
        String baseUsername = (user.getName().toLowerCase().length() > 4? user.getName().toLowerCase().substring(0,4):user.getName().toLowerCase()) +
                user.getLastName().toLowerCase().substring(0, 3);
        String uniquePart = user.getNumberIdentification().substring(0, 4);
        return baseUsername.replaceAll("\\s+", "") + (user.getId()-1);
    }

    public String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }
        return password.toString();
    }

    public List<UserEntity> getUsersByRole(String role) {
        switch (role) {
            case "Administrador":

                return userRepo.findByRole(ERole.valueOf("ADMIN"));
            case "Tecnico interno":
                return userRepo.findByRole(ERole.valueOf("INTERNAL_TECHNICIAN"));
            case "Tecnico externo":
                return userRepo.findByRole(ERole.valueOf("EXTERNAL_TECHNICIAN"));
            default:
                return null;
        }
    }

}
