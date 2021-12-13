package ru.ydubovitsky.egblogspring.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.egblogspring.entity.Role;
import ru.ydubovitsky.egblogspring.entity.User;
import ru.ydubovitsky.egblogspring.security.entity.AppUser;

import java.util.ArrayList;
import java.util.List;

@Service("mock")
public class AppUserServiceMockDataImpl implements AppUserService {

    private final PasswordEncoder passwordEncoder;

    public AppUserServiceMockDataImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = new User(
                null,
                "u",
                "u",
                "email@mail.ru",
                null,
                null
        );

        //FIXME Переделать на стримы
        List<Role> roleList = user.getRoleList();
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("ADMIN"));
//        roleList.forEach(role -> grantedAuthorityList.add(new SimpleGrantedAuthority(role.getName())));

        AppUser appUser = new AppUser(
                passwordEncoder.encode(user.getPassword()),
                user.getUsername(),
                grantedAuthorityList,
                true,
                true,
                true,
                true
                );

        return appUser;
    }

}
