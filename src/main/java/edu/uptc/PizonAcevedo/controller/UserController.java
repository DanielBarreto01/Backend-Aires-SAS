package edu.uptc.PizonAcevedo.controller;

import edu.uptc.PizonAcevedo.domain.model.ERole;
import edu.uptc.PizonAcevedo.domain.model.Roles;
import edu.uptc.PizonAcevedo.domain.model.UserEntity;
import edu.uptc.PizonAcevedo.service.TokenBlacklistService;
import edu.uptc.PizonAcevedo.service.UserMgmt;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserController {

//    @Value("${message}")
//    private String message;

    @Autowired
    private UserMgmt userMgmt;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

//    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
//    public String welcome(){
//        return message;
//    }

    @GetMapping("/hello-1")
    public String helloAdmin(){
        return "Hello ADMIN";
    }

    @GetMapping("/hello-2")
    public String helloUser(){
        return "Hello USER";
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
//    public ResponseEntity createUser(@RequestBody Map<String, Object> requestData) {
//        System.out.println(requestData);
//    return new ResponseEntity<>("El usuario fue creado correctamente entra bien", HttpStatus.NOT_FOUND);
//
//
//    }


    //@PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createUser(@RequestBody Map<String, Object> requestData) {
        try {
            userMgmt.saveUser(UserEntity.builder()
                    .name((String) requestData.get("name"))
                    .lastName((String) requestData.get("lastName"))
                    .typeIdentification((String) requestData.get("typeIdentification"))
                    .numberIdentification((String) requestData.get("numberIdentification"))
                    .email((String) requestData.get("email"))
                    .phoneNumber(Long.parseLong((String) requestData.get("phoneNumber")))
                    .address((String) requestData.get("address"))
                    .pathImage((String) requestData.get("pathImage"))
                    .roles(setRoles((Collection)requestData.get("roles"))).build());
            return new ResponseEntity<>("El usuario fue creado correctamente", HttpStatus.OK); // Respuesta de éxito
        } catch (DataIntegrityViolationException e) {
            String errorMessage = e.getRootCause().getMessage();
            if (errorMessage.contains("email")) {
                return new ResponseEntity<>("El correo electrónico ya está registrado.", HttpStatus.NOT_FOUND);
            } else if (errorMessage.contains("number_identification")){
                return new ResponseEntity<>("Este número de identificación ya está registrado.", HttpStatus.NOT_FOUND);
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>("Error al registrar el usuario, intente de nuevo.", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json")
    public void logout(HttpServletRequest request) {
        String tokenHeader = request.getHeader("Authorization");
        if(tokenHeader != null && tokenHeader.startsWith("Bearer ")){
            String token = tokenHeader.substring(7);
            tokenBlacklistService.addToBlacklist(token);
        }
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET, produces = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getUsers(){
        return new ResponseEntity<>(userMgmt.getUsers(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/ShowUserRoles/{role}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity shiftByUserId (@PathVariable String role ){
        userMgmt.getUsersByRole(role);
        return new ResponseEntity<>(userMgmt.getUsersByRole(role), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/userUpdate/{id}", method = RequestMethod.PATCH, produces = "application/json")
    public ResponseEntity updateUser(@PathVariable int id, @RequestBody Map<String, Object> requestData) {
        try {
            userMgmt.updateUser(id, UserEntity.builder()
                    .name((String) requestData.get("name"))
                    .lastName((String) requestData.get("lastName"))
                    .typeIdentification((String) requestData.get("typeIdentification"))
                    .numberIdentification((String) requestData.get("numberIdentification"))
                    .email((String) requestData.get("email"))
                    .phoneNumber(Long.parseLong((String) requestData.get("phoneNumber")))
                    .address((String) requestData.get("address"))
                    .pathImage((String) requestData.get("pathImage"))
                    .userStatus((boolean) requestData.get("userStatus"))
                    .roles(setRoles((Collection)requestData.get("roles"))).build());
            return new ResponseEntity<>("El usuario se actualizo exitasamente", HttpStatus.OK); // Respuesta de éxito
        } catch (DataIntegrityViolationException e) {
            String errorMessage = e.getRootCause().getMessage();
            if (errorMessage.contains("email")) {
                return new ResponseEntity<>("El correo electrónico ya está registrado.", HttpStatus.NOT_FOUND);
            } else if (errorMessage.contains("number_identification")){
                return new ResponseEntity<>("Este número de identificación ya está registrado.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Error al actualizar la informacón del usuario, intente de nuevo.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private Set<Roles> setRoles(Collection<String> roles){
        return roles.stream()
                .map(role -> Roles.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());
    }
}
