package ru.ydubovitsky.engineerBlog.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.ydubovitsky.engineerBlog.config.VariablesConfig;
import ru.ydubovitsky.engineerBlog.entity.AppUser;
import ru.ydubovitsky.engineerBlog.entity.enums.Role;
import ru.ydubovitsky.engineerBlog.repository.AppUserRepository;

import java.util.Set;

@RequiredArgsConstructor
@Slf4j
@Component
public class CustomCommandLineRunner implements CommandLineRunner {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VariablesConfig variablesConfig;

    @Override
    public void run(String... args) throws Exception {
        userRepository.findByUsername(variablesConfig.getInitAdminName())
                .ifPresentOrElse(
                        user -> log.info("Admin exists already"),
                        this::createAdminUserOnStartup);
    }

    private void createAdminUserOnStartup() {
        AppUser admin = AppUser.builder()
                .username(variablesConfig.getInitAdminName())
                .password(passwordEncoder.encode(variablesConfig.getInitAdminPassword()))
                .roles(Set.of(Role.ADMIN))
                .build();
        userRepository.save(admin);
        log.info("Admin user created");
    }
}

