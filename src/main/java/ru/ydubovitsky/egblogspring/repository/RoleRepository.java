package ru.ydubovitsky.egblogspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ydubovitsky.egblogspring.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
