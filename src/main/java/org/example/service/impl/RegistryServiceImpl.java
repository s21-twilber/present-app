package org.example.service.impl;

import org.example.entity.ApplicationRegistry;
import org.example.entity.PresentApplication;
import org.example.repository.RegistryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistryServiceImpl {

    private final RegistryRepository registryRepository;

    public RegistryServiceImpl(RegistryRepository registryRepository) {
        this.registryRepository = registryRepository;
    }



    public Optional<ApplicationRegistry> getRepository(String email) {
        return registryRepository.findByEmpEmail(email);
    }

    public List<ApplicationRegistry> findAll() {
        return registryRepository.findAll();
    }

    public ResponseEntity<?> addApplication(PresentApplication app) {

        ApplicationRegistry application =
                new ApplicationRegistry(app.getId(), app.getEmpId(), app.getStatus().getName(), app.getResponsibleId());
        return ResponseEntity.ok(registryRepository.save(application));
    }
}
