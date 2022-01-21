package ru.ydubovitsky.engineerBlog.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.ydubovitsky.engineerBlog.entity.AppUser;

public interface AppUserService {

    UserDetails loadAppUser(String username);

    AppUser getById(Integer id);

    AppUser getByUsername(String username);
}
