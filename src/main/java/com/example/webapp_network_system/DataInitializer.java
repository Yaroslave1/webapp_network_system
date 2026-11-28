package com.example.webapp_network_system;

import com.example.webapp_network_system.entity.Role;
import com.example.webapp_network_system.entity.User;
import com.example.webapp_network_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== ПРОВЕРКА БАЗЫ ДАННЫХ ===");

        if (userRepository.count() == 0) {
            System.out.println("=== БАЗА ПУСТА. СОЗДАЮ ПОЛЬЗОВАТЕЛЕЙ... ===");

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            admin.setRoles(Set.of(Role.ROLE_ADMIN));
            userRepository.save(admin);

            User client = new User();
            client.setUsername("client");
            client.setPassword(passwordEncoder.encode(client.getPassword()));
            client.setRoles(Set.of(Role.ROLE_USER));
            userRepository.save(client);

            System.out.println("=== ПОЛЬЗОВАТЕЛИ УСПЕШНО СОЗДАНЫ! ===");
        } else {
            System.out.println("=== В БАЗЕ УЖЕ ЕСТЬ ПОЛЬЗОВАТЕЛИ. ПРОПУСК. ===");
        }
    }
}