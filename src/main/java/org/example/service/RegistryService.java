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

    @Autowired
    public RegistryService(RegistryRepository registryRepository) {
        this.registryRepository = registryRepository;
    }

    public Optional<ApplicationRegistry> getRepository(String email) {
        return registryRepository.findByEmpEmail(email);
    }

    public List<ApplicationRegistry> findAll() {
        return registryRepository.findAll();
    }
}
