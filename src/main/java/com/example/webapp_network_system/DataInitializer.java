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
        System.out.println(">>> Проверка наличия пользователей в базе...");

        // Если таблица users пуста
        if (userRepository.count() == 0) {
            System.out.println(">>> База пуста. Создаю тестовых пользователей...");

            // 1. Создаем Админа
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            // ВНИМАНИЕ: Проверьте, как называются роли в вашем Enum Role (ADMIN или ROLE_ADMIN)
            admin.setRoles(Set.of(Role.ROLE_ADMIN));
            userRepository.save(admin);

            // 2. Создаем Клиента
            User client = new User();
            client.setUsername("client");
            client.setPassword(passwordEncoder.encode("client123"));
            client.setRoles(Set.of(Role.ROLE_USER));
            userRepository.save(client);

            System.out.println(">>> Пользователи (admin / admin123 и client / client123) успешно созданы!");
        } else {
            System.out.println(">>> Пользователи уже существуют в базе. Пропускаю инициализацию.");
        }
    }
}