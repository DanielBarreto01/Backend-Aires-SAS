package edu.uptc.PizonAcevedo.controller;

import edu.uptc.PizonAcevedo.domain.model.ERole;
import edu.uptc.PizonAcevedo.domain.model.Roles;
import edu.uptc.PizonAcevedo.domain.model.UserEntity;
import edu.uptc.PizonAcevedo.service.UserMgmt;

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

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createUser(@RequestBody Map<String, Object> requestData) {
        try {
            userMgmt.saveUser(UserEntity.builder()
                    .name((String) requestData.get("name"))
                    .lastName((String) requestData.get("lastName"))
                    .typeIdentification((String) requestData.get("typeIdentification"))
                    .numberIdentification((String) requestData.get("numberIdentification"))
                    .role((String) requestData.get("role"))
                    .email((String) requestData.get("email"))
                    .phoneNumber((Long) requestData.get("phoneNumber"))
                    .roles(setRoles((Collection)requestData.get("roles"))).build());
            return new ResponseEntity<>("El usuario fue creado correctamente", HttpStatus.OK); // Respuesta de éxito
        } catch (DataIntegrityViolationException e) {
            String errorMessage = e.getRootCause().getMessage();
            if (errorMessage.contains("email")) {
                return new ResponseEntity<>("El correo electrónico ya está registrado.", HttpStatus.NOT_FOUND);
            } else if (errorMessage.contains("number_identification")){
                return new ResponseEntity<>("Este numero de identificacion ya está registrado.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Error al registar usuario.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET, produces = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getUsers(){
        return new ResponseEntity<>(userMgmt.getUsers(), HttpStatus.OK);
    }

    private Set<Roles> setRoles(Collection<String> roles){
        return roles.stream()
                .map(role -> Roles.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());
    }
}
