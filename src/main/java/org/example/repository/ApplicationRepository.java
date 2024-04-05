package org.example.repository;

import org.example.entity.Application;
import org.example.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface ApplicationRepository {

    Optional<Application> findByEmpId(String email);
}
