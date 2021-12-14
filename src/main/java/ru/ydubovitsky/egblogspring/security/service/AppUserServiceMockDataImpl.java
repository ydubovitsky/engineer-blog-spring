package ru.ydubovitsky.egblogspring.security.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.egblogspring.entity.Role;
import ru.ydubovitsky.egblogspring.entity.User;
import ru.ydubovitsky.egblogspring.security.entity.AppUser;
import ru.ydubovitsky.egblogspring.service.RoleService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("mock")
public class AppUserServiceMockDataImpl implements AppUserService {

    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public AppUserServiceMockDataImpl(
            PasswordEncoder passwordEncoder,
            RoleService roleService
    ) {
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = new User(
                null,
                "user",
                "password",
                "mail@email.com",
                null,
                null,
                true,
                true,
                true,
                true
        );

        List<Role> allRoles = roleService.getAllRoles();
        Set<SimpleGrantedAuthority> grantedAuthority;

        if (allRoles != null) {
            grantedAuthority = allRoles.stream()
                    .flatMap(role -> role.getPermissionList()
                            .stream())
                    .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                    .collect(Collectors.toSet());
        } else {
            grantedAuthority = Set.of(new SimpleGrantedAuthority("ADMIN"));
        }

        AppUser appUser = new AppUser(
                passwordEncoder.encode(user.getPassword()),
                user.getUsername(),
                grantedAuthority,
                user.getIsAccountNonExpired(),
                user.getIsAccountNonLocked(),
                user.getIsCredentialsNonExpired(),
                user.getIsEnabled()
        );

        return appUser;
    }

}
