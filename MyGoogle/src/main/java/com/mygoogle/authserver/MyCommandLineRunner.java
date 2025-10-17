package com.mygoogle.authserver;




import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mygoogle.authserver.entity.User;
import com.mygoogle.authserver.repository.UserRepository;

//@Configuration
public class MyCommandLineRunner {

    @Bean
    public CommandLineRunner seedUsers(UserRepository repo) {
        return args ->
        {
            if (repo.count() == 0) {
            	
            	repo.save(new User(null, "ramesh", "a", "UPLOADER"));
            	repo.save(new User(null, "suresh", "a", "UPLOADER"));
                
                repo.save(new User(null, "amit", "a", "REVIEWER"));
                repo.save(new User(null, "neha", "a", "REVIEWER"));
                
                repo.save(new User(null, "admin", "a", "ADMIN"));
                System.out.println("Sample users created.");
            } else {
                System.out.println("Users already exist. Skipping seeding.");
            }
        };
    }
}

