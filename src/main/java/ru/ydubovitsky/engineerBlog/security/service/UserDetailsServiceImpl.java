package ru.ydubovitsky.engineerBlog.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.engineerBlog.service.user.AppUserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserService appUserService;

    public UserDetailsServiceImpl(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserService.getAppUserByName(username);
    }
}
