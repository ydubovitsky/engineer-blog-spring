package ru.ydubovitsky.egblogspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ydubovitsky.egblogspring.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}
