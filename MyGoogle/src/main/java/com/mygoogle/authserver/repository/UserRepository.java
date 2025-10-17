package com.mygoogle.authserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mygoogle.authserver.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
    public User findByUsername(String username);
    
    public User findByUsernameAndPassword(String username,String password);
    
}

