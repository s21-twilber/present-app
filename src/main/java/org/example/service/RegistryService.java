package org.example.service;

import org.example.entity.ApplicationRegistry;
import org.example.entity.Role;
import org.example.enums.RolesEnum;
import org.example.repository.RegistryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistryService {

    private final RegistryRepository registryRepository;

    public RegistryService(RegistryRepository registryRepository) {
        this.registryRepository = registryRepository;
    }

    public ApplicationRegistry getRepository(String email) {
        if (registryRepository == null) {
            return null;
        }
        return registryRepository.findByEmpEmail(email).get();
    }

    public List<ApplicationRegistry> findAll() {
        return registryRepository.findAll();
    }
}
