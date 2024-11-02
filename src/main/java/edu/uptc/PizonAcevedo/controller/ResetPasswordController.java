package edu.uptc.PizonAcevedo.controller;


import edu.uptc.PizonAcevedo.service.userServices.ServiceResetPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/reset-password")

public class ResetPasswordController {

    @Autowired
    private ServiceResetPassword serviceResetPassword;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity generateResetPassword(@RequestBody Map<String, String> requestData) {
        try {
            serviceResetPassword.generateResetPassword(requestData.get("email"));
            return ResponseEntity.ok().
                    build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @RequestMapping(value = "/validateStatusToken/{token}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity validateStatusToken(@PathVariable String token) {
        try {
            Map response = serviceResetPassword.validateStatusToken(token);
            System.out.println(response);
            if(response != null){
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.badRequest().
                    build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/validateCode/{token}", method = RequestMethod.GET, produces = "application/json")
    public  ResponseEntity validateCode(@PathVariable String token, @RequestParam String verificationCode) {
        try {
            if(serviceResetPassword.validateCode(token, verificationCode)){
                return new ResponseEntity<>("Código de verificación correcto", HttpStatus.OK);
            }
            return new ResponseEntity<>("Código de verificación erroneo", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }


    @RequestMapping(value = "/changePassword/{token}", method = RequestMethod.POST, produces = "application/json")
    public  ResponseEntity changePassword(@PathVariable String token, @RequestBody Map<String, String> requestData) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            if(serviceResetPassword.changePasswordService(token, passwordEncoder.encode(requestData.get("password")), requestData.get("verificationCode"))){
                return new ResponseEntity<>("Se cambio la contraseña correctamente", HttpStatus.OK);
            }
            return new ResponseEntity<>("Código de verificación erroneo", HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();

        }
    }
}
