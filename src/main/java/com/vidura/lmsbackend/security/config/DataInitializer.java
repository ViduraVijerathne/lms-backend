package com.vidura.lmsbackend.security.config;

import com.vidura.lmsbackend.security.entity.Role;
import com.vidura.lmsbackend.security.entity.User;
import com.vidura.lmsbackend.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Create initial users if they don't exist
        createUserIfNotExists("admin@gmail.com", "admin123", Role.ADMIN);
//        createUserIfNotExists("cashier", "admin123", Role.STUDENT);
//        createUserIfNotExists("cashier", "admin123", Role.STUDENT);
//        createUserIfNotExists("dev", "admin123", Role.DEV);

        System.out.println("=".repeat(60));
        System.out.println("Initial users created successfully!");
        System.out.println("Username: admin    | Password: admin123 | Role: ADMIN");
//        System.out.println("Username: cashier  | Password: admin123 | Role: CASHIER");
//        System.out.println("Username: dev      | Password: admin123 | Role: DEV");
        System.out.println("=".repeat(60));
    }

    private void createUserIfNotExists(String username, String password, Role role) {
        if (!userRepository.existsByUsername(username)) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole(role);
            userRepository.save(user);
        }
    }
}
