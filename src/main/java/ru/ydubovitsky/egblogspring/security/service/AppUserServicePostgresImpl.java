package ru.ydubovitsky.egblogspring.security.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.egblogspring.entity.User;
import ru.ydubovitsky.egblogspring.security.entity.AppUser;
import ru.ydubovitsky.egblogspring.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service("postgres")
public class AppUserServicePostgresImpl implements AppUserService {

    private final UserService userService;

    public AppUserServicePostgresImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.getUser(username);

        List<SimpleGrantedAuthority> userPermissions = user.getRoleList()
                .stream()
                .flatMap(role -> role.getPermissionList().stream())
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toList());

        AppUser appUser = new AppUser(
                user.getPassword(),
                user.getUsername(),
                userPermissions,
                user.getIsAccountNonExpired(),
                user.getIsAccountNonLocked(),
                user.getIsCredentialsNonExpired(),
                user.getIsEnabled()
        );

        return appUser;
    }
}
