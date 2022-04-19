package ru.ydubovitsky.engineerBlog.service.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.engineerBlog.entity.AppUser;
import ru.ydubovitsky.engineerBlog.repository.AppUserRepository;

@Slf4j
@Service("postgres")
@AllArgsConstructor
public class AppUserServicePostgresImpl implements AppUserService {

    private final AppUserRepository appUserRepository;

    @Override
    public AppUser getAppUserByName(String username) {
        return appUserRepository.findByUsername(username);
    }

}
