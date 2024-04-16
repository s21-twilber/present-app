package org.example.repository;

import org.example.entity.PresentApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<PresentApplication, Long> {

    PresentApplication save(PresentApplication app);

    List<PresentApplication> findAllByEmpId(Long id);

    List<PresentApplication> findAllByResponsibleId(Long id);

    PresentApplication findByEmpIdAndId(Long empId, Long id);

    Optional<PresentApplication> findById(Long id);

}
