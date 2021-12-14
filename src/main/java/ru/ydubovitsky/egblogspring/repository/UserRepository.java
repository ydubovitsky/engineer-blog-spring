package ru.ydubovitsky.egblogspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ydubovitsky.egblogspring.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}
