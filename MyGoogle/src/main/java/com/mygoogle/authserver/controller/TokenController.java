package com.mygoogle.authserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mygoogle.authserver.dto.ErrorResponseDTO;
import com.mygoogle.authserver.service.GenerateJWTTokenService;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("mygoogle/oauth")
public class TokenController {

    @Autowired
    private AuthenticationManager authManager;
    
    @Autowired
    private GenerateJWTTokenService tokenService;

    @PostMapping("/token")
    public ResponseEntity<?> getToken(@RequestParam String username,
                                      @RequestParam String password,
                                      @RequestParam String client_id,
                                      @RequestParam String client_secret) 
    {
    	
    	System.out.println("Inside getToken Token Controller");
    	System.out.println("username & password  "+username+" "+password);
    	System.out.println("clientId :- "+client_id);
    	System.out.println("client_secret :- "+client_secret);

        if (!client_id.equals("myapp_client") || !client_secret.equals("myapp_secret")) {
            
        	ErrorResponseDTO dto = new ErrorResponseDTO();
        	dto.setErrorComment("Invalid client credentials");
        	
        	ResponseEntity<ErrorResponseDTO> resp = new ResponseEntity<>(dto,HttpStatus.UNAUTHORIZED);
        	return resp;
            		
            		
        }

        try {
            Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwtToken(auth.getName());

            Map<String, Object> response = new HashMap<>();
            response.put("access_token", token);
            response.put("token_type", "Bearer");
            response.put("expires_in", 1*60*60);// 1hr .. it takes value in second

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) 
        {
        	
         	ErrorResponseDTO dto = new ErrorResponseDTO();
        	dto.setErrorComment("Invalid client credentials [Google Token Controller Exception ], "+e.getMessage());
        	
        	ResponseEntity<ErrorResponseDTO> resp = new ResponseEntity<>(dto,HttpStatus.UNAUTHORIZED);
        	return resp;
        }
    }

   
}
