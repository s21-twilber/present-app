package org.example.service;


import org.example.entity.Role;
import org.example.enums.RolesEnum;
import org.example.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;


    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getUserRole() {
        System.out.println(roleRepository.count());
        return roleRepository.findByName(RolesEnum.valueOf("ROLE_USER")).get();
    }
}
