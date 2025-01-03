package edu.uptc.PizonAcevedo.service.userServices;

import edu.uptc.PizonAcevedo.domain.model.userModel.Credential;
import edu.uptc.PizonAcevedo.domain.model.userModel.ERole;
import edu.uptc.PizonAcevedo.domain.model.userModel.Roles;
import edu.uptc.PizonAcevedo.domain.repository.repositoryUser.CredentialRepository;
import edu.uptc.PizonAcevedo.domain.repository.repositoryUser.UserRepository;
import edu.uptc.PizonAcevedo.domain.model.userModel.UserEntity;
import edu.uptc.PizonAcevedo.service.email.IEmailService;
import edu.uptc.PizonAcevedo.util.Email;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserMgmt {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz0123456789-.+!?_@";
    private static final int PASSWORD_LENGTH = 8;

    @Autowired
    UserRepository userRepo;

    @Autowired
    IEmailService emailService;

    @Autowired
    CredentialRepository credentialRepository;

    public void saveUser(UserEntity user) throws MessagingException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String userName =generateUniqueUsername(user);
        String password = generateRandomPassword();
        if(!userRepo.existsByEmailAndNumberIdentification(user.getEmail(), user.getNumberIdentification())){
            emailService.sendEmail(user.getEmail(), Email.emailSubject(), Email.bodyEmail(user.getName(), user.getLastName(), userName, password));
        }
        password = passwordEncoder.encode(password);
        userRepo.save(user);
        Credential credential = Credential.builder()
                .userName(userName)
                .user(user)
                .password(password)
                .build();
        credentialRepository.save(credential);
//        try {
//            emailService.sendEmail(user.getEmail(), Email.emailSubject(), Email.bodyEmail(user.getName(), user.getLastName(), credential.getUserName(), credential.getPassword()));
//            credential.setPassword(new BCryptPasswordEncoder().encode(credential.getPassword()));
//            credentialRepository.save(credential);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
    }

    public UserEntity findUserById(int id){
            return userRepo.findUserById(id);
    }

    public List<UserEntity> getUsers(){
        return userRepo.findAll();
    }



    public String generateUniqueUsername(UserEntity user) {
        String baseUsername = (user.getName().contains(" ")? user.getName().split(" ")[0] : user.getName()) + "."
                + (user.getLastName().contains(" ")? user.getLastName().split(" ")[0] : user.getLastName());  // Generar base del nombre
        String username = baseUsername.replaceAll("\\s+", "").toLowerCase();
        Random random = new Random();
        int suffix = random.nextInt(9900) + 100;  // Número aleatorio entre 100 y 999

        // Verificar si ya existe y ajustar en caso de que sea necesario
        while (credentialRepository.existsByUserName(username)) {
            username = baseUsername + suffix;
            suffix = random.nextInt(900) + 100;  // Generar un nuevo número si es necesario
        }
        return username;
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

    @Transactional
    public void updateUser(int id, UserEntity user){
        //userRepo.findUserById(id);

        // Llamada al repositorio para actualizar los campos
        userRepo.updateUser(id, user.getName(), user.getLastName(), user.getTypeIdentification(),
                user.getNumberIdentification(), user.getEmail(), user.getPhoneNumber(),
                user.getAddress(), user.getPathImage(), user.isUserStatus());

        //System.out.println((userRepo.findRoleIdByUserId(id).get(0)) + "         id Role");
        Set<ERole> rolesUser = user.getRoles().stream().map(Roles::getName).collect(Collectors.toSet());
        userRepo.updateRoles((userRepo.findRoleIdByUserId(id)).get(0),rolesUser.iterator().next());


    }

    @Transactional
    public List<UserEntity> getUsersWithoutAdminRole() throws Exception {
        return userRepo.findUsersWithoutRoleAdmin(ERole.ADMIN);
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
