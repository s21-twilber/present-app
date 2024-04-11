package org.example.service.impl;

import org.example.entity.Role;
import org.example.enums.RolesEnum;
import org.example.repository.RoleRepository;
import org.example.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getUserRole() {
        return roleRepository.findByName(RolesEnum.valueOf("ROLE_USER")).get();
    }

    public Role getCoordinatorRole() {
        return roleRepository.findByName(RolesEnum.valueOf("ROLE_COORDINATOR")).get();
    }
}
