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
    public AppUser getAppUserByName(String username) {
        AppUser appUser = AppUser.builder()
                .username("u")
                .password(passwordEncoder.encode("u"))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .roles(Set.of(Role.ADMIN))
                .build();
        return appUserRepository.save(appUser);
    }
}
