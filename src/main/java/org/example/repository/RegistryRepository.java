package org.example.repository;

import org.example.entity.ApplicationRegistry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegistryRepository extends JpaRepository<ApplicationRegistry, Long> {

    Optional<ApplicationRegistry> findByEmpEmail(String email);
    List<ApplicationRegistry> findAll();
}
