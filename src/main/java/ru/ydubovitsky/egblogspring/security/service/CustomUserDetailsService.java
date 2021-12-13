package ru.ydubovitsky.egblogspring.security.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserService appUserService;

    public CustomUserDetailsService(@Qualifier("mock") AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserService.loadUserByUsername(username);
    }
}
