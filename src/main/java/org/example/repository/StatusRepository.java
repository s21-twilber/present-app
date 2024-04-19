package org.example.repository;

import org.example.entity.Status;
import org.example.enums.StatusesEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

    Optional<Status> findStatusByName(StatusesEnum name);
}
