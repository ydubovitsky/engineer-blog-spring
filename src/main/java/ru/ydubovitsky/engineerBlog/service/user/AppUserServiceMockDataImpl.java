package ru.ydubovitsky.engineerBlog.service.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.engineerBlog.entity.AppUser;
import ru.ydubovitsky.engineerBlog.entity.Contacts;
import ru.ydubovitsky.engineerBlog.entity.enums.Role;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Slf4j
@Primary
@Service("mock")
@AllArgsConstructor
public class AppUserServiceMockDataImpl implements AppUserService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser getAppUserByName(String username) {
        AppUser appUser = this.getMockAppUserCollection().stream()
                .filter(mockUser -> mockUser.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("User with name %s not found", username)));

        log.info(String.format("UserDetails with name: %s loaded", appUser.getUsername()));
        return appUser;
    }

    private Collection<AppUser> getMockAppUserCollection() {
        AppUser root = AppUser.builder()
                .username("root")
                .password(passwordEncoder.encode("root"))
                .roles(Set.of(Role.ADMIN, Role.SUPER_ADMIN, Role.USER))
                .contacts(Contacts.builder()
                        .address("город Москва, ул. Арбат, дом. 33")
                        .email("root@mail.ru")
                        .phone("+5(333)365-124-54365")
                        .webSite("www.root.com")
                        .build()
                )
                .about("Профессор Императорского Санкт-Петербургского университета; член-корреспондент (по разряду «физический») Императорской Санкт-Петербургской Академии наук. Среди самых известных открытий — периодический закон химических элементов, один из фундаментальных законов мироздания, неотъемлемый для всего естествознания. Автор классического труда «Основы химии»[7]. Тайный советник.")
                .isEnabled(true)
                .isCredentialsNonExpired(true)
                .isAccountNonLocked(true)
                .isAccountNonExpired(true)
                .build();

        AppUser superAdmin = AppUser.builder()
                .username("s")
                .password(passwordEncoder.encode("s"))
                .roles(Set.of(Role.SUPER_ADMIN))
                .isEnabled(true)
                .isCredentialsNonExpired(true)
                .isAccountNonLocked(true)
                .isAccountNonExpired(true)
                .build();

        AppUser user = AppUser.builder()
                .username("u")
                .password(passwordEncoder.encode("u"))
                .roles(Set.of(Role.ADMIN))
                .isEnabled(true)
                .isCredentialsNonExpired(true)
                .isAccountNonLocked(true)
                .isAccountNonExpired(true)
                .build();

        AppUser admin = AppUser.builder()
                .username("a")
                .password(passwordEncoder.encode("a"))
                .roles(Set.of(Role.ADMIN))
                .isEnabled(true)
                .isCredentialsNonExpired(true)
                .isAccountNonLocked(true)
                .isAccountNonExpired(true)
                .build();

        return List.of(root, superAdmin, admin, user);
    }

}
