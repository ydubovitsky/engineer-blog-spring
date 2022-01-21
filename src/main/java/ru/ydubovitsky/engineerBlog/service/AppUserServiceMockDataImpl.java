package ru.ydubovitsky.engineerBlog.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.engineerBlog.entity.AppUser;
import ru.ydubovitsky.engineerBlog.entity.Role;
import ru.ydubovitsky.engineerBlog.repository.AppUserRepository;

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
                Role.ADMIN,
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
        return appUserRepository.findByUsername(username);
    }
}
