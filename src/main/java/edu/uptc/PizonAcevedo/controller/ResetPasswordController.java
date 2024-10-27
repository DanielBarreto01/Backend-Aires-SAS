package edu.uptc.PizonAcevedo.controller;


import edu.uptc.PizonAcevedo.service.ServiceResetPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
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
            Date date = serviceResetPassword.validateStatusToken(token);
            if(date != null){
                return ResponseEntity.ok(new HashMap<>() {{
                    put("date", date.getTime());
                }});
            }
            return ResponseEntity.badRequest().
                    build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
    @RequestMapping(value = "/changePassword/{token}", method = RequestMethod.POST, produces = "application/json")

    public  ResponseEntity changePassword(@PathVariable String token, @RequestBody Map<String, String> requestData) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try {
            if(serviceResetPassword.changePasswordService(token, passwordEncoder.encode(requestData.get("password")))){
                return new ResponseEntity<>("Se cambio la contraseña correctamente", HttpStatus.OK);
            }
            return ResponseEntity.badRequest().
                    build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();

        }
    }
}