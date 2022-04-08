package ru.ydubovitsky.engineerBlog.service.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.engineerBlog.entity.AppUser;
import ru.ydubovitsky.engineerBlog.repository.AppUserRepository;
import ru.ydubovitsky.engineerBlog.service.user.AppUserService;

@Slf4j
@Service("postgres")
@AllArgsConstructor
public class AppUserServicePostgresImpl implements AppUserService {

    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadAppUser(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public AppUser getById(Integer id) {
        AppUser appUser = appUserRepository.getById(id);
        log.info(String.format("%s loaded", appUser.getUsername()));
        return appUser;
    }

    @Override
    public AppUser getByUsername(String username) {
        return null;
    }
}
