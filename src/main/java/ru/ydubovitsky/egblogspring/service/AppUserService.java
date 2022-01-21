package ru.ydubovitsky.egblogspring.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.ydubovitsky.egblogspring.entity.AppUser;

public interface AppUserService {

    UserDetails loadAppUser(String username);

    AppUser getById(Integer id);

    AppUser getByUsername(String username);
}
