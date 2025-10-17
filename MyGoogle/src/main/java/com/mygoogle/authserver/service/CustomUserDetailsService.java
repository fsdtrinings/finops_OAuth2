package com.mygoogle.authserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.mygoogle.authserver.entity.User;
import com.mygoogle.authserver.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User googleuser = repo.findByUsername(username);
        if(googleuser != null) 
        {
        	  return org.springframework.security.core.userdetails.User
        			  .withUsername(googleuser.getUsername())
                      .password(googleuser.getPassword())
                      .roles(googleuser.getRole())
                      .build();
        }
        else
        {
        	throw  new UsernameNotFoundException("User not found");
        }
      
    }
}

