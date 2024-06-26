package org.example.service.impl;

import org.example.entity.Role;
import org.example.enums.RolesEnum;
import org.example.exception.NotFoundException;
import org.example.repository.RoleRepository;
import org.example.service.RoleService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRole(RolesEnum role) {
        return roleRepository.findRoleByName(role).get();
    }

    public Role getRoleById(int id) {
        return roleRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Not found users with this role"));
    }

}
