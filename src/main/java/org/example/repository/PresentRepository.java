package org.example.repository;

import org.example.entity.Present;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PresentRepository extends JpaRepository<Present, Long> {

    Present save(Present app);

    List<Present> findAllByEmployee_Id(Long id);

    List<Present> findAllByResponsibleId(Long id);

    Optional<Present> findByEmployee_IdAndId(Long empId, Long id);

    Optional<Present> findById(Long id);

    void deletePresentById(Long id);

}
