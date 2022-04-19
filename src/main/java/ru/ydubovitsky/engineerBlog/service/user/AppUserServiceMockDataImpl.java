package ru.ydubovitsky.engineerBlog.service.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.engineerBlog.entity.AppUser;
import ru.ydubovitsky.engineerBlog.entity.enums.Role;
import ru.ydubovitsky.engineerBlog.repository.AppUserRepository;

import java.util.Set;

@Slf4j
@Primary
@Service("mock")
@AllArgsConstructor
public class AppUserServiceMockDataImpl implements AppUserService {

    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadAppUser(String username) {
        AppUser appUser = AppUser.builder()
                .username("u")
                .password(passwordEncoder.encode("u"))
                .roles(Set.of(Role.ADMIN, Role.SUPER_ADMIN, Role.USER))
                .isEnabled(true)
                .isCredentialsNonExpired(true)
                .isAccountNonLocked(true)
                .isAccountNonExpired(true)
                .build();

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
