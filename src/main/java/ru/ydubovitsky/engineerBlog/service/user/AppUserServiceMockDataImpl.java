package ru.ydubovitsky.engineerBlog.service.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.engineerBlog.entity.AppUser;
import ru.ydubovitsky.engineerBlog.entity.enums.Role;
import ru.ydubovitsky.engineerBlog.repository.AppUserRepository;

import java.util.Set;

@Slf4j
@Service("mock")
@AllArgsConstructor
public class AppUserServiceMockDataImpl implements AppUserService {

    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadAppUser(String username) {
        AppUser appUser = new AppUser(
                null,
                "u",
                passwordEncoder.encode("u"),
                "mail@email.com",
                null,
                Set.of(Role.ADMIN, Role.SUPER_ADMIN, Role.USER),
                null,
                true,
                true,
                true,
                true
        );
        log.info(String.format("UserDetails with name: %s loaded", appUser.getUsername()));
        appUserRepository.save(appUser);
        return appUser;
    }

    @Override
    public AppUser getById(Integer id) {
        return appUserRepository.getById(id);
    }

    @Override
    public AppUser getByUsername(String username) {
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException(String.format("User with name - %s not found", username)));
    }
}
