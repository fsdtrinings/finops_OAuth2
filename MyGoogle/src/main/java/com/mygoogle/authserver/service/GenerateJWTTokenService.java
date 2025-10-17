package com.mygoogle.authserver.service;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mygoogle.authserver.entity.User;
import com.mygoogle.authserver.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jwt.*;

@Service
public class GenerateJWTTokenService {

	@Autowired
	UserRepository userRepo;
	
	public String generateJwtToken(String username) 
	{
		
		User user = userRepo.findByUsername(username);
		String role = user.getRole();
		
	    System.out.println("Generating JWT Token for "+username);
		try {
	        // Create HMAC signer
	        JWSSigner signer = new MACSigner("mysecretkey1234567890mysecretkey1234567890"); // 32+ chars
	        System.out.println("1. key created ");
	        // Prepare JWT with claims
	        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
	            .subject(username)
	            .issuer("mygoogle.com")
	            .claim("role", role)
	            .expirationTime(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) 
	            .issueTime(new Date())
	            .build();

	        System.out.println("2. Claims set build "+claimsSet.toString());
	        
	        // Create JWS object
	        SignedJWT signedJWT = new SignedJWT(
	            new JWSHeader(JWSAlgorithm.HS256),
	            claimsSet
	        );
	        System.out.println("3. JWS Signed ");
	        
	        // Apply the HMAC
	        signedJWT.sign(signer);

	        System.out.println("4. Process complete , about to send token");	
	        
	        // Serialize to compact form
	        return signedJWT.serialize();

	    } catch (JOSEException e) {
	        throw new RuntimeException("Error generating JWT [GenerateJWTTokenService] Exception", e);
	    }
	}

	
}
