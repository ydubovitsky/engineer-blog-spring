package ru.ydubovitsky.egblogspring.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.egblogspring.entity.AppUser;
import ru.ydubovitsky.egblogspring.repository.AppUserRepository;

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
        //FIXME Метод реализовать
        return null;
    }

    @Override
    public AppUser getByUsername(String username) {
        return null;
    }
}
