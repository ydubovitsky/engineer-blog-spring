package ru.ydubovitsky.egblogspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ydubovitsky.egblogspring.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    AppUser findByUsername(String username);

}
