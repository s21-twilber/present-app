package org.example.service;

import org.example.entity.Role;
import org.example.enums.RolesEnum;


public interface RoleService {

    Role getRole(RolesEnum role);
}
