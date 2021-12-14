package ru.ydubovitsky.egblogspring.service;

import org.springframework.stereotype.Service;
import ru.ydubovitsky.egblogspring.entity.Role;
import ru.ydubovitsky.egblogspring.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role addRole(Role role) {
        Role save = roleRepository.save(role);
        return save;
    }

    public Role getRoleById(Integer id) {
        Role role = roleRepository.getById(id);
        return role;
    }

    public List<Role> getAllRoles() {
        List<Role> all = roleRepository.findAll();
        return all;
    }
}
