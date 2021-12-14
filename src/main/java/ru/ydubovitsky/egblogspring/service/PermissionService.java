package ru.ydubovitsky.egblogspring.service;

import org.springframework.stereotype.Service;
import ru.ydubovitsky.egblogspring.entity.Permission;
import ru.ydubovitsky.egblogspring.repository.PermissionRepository;



@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Permission addPermission(Permission permission) {
        Permission savedPermission = permissionRepository.save(permission);
        return savedPermission;
    }
}
