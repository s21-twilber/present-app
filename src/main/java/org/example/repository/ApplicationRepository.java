package org.example.repository;

import org.example.entity.PresentApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<PresentApplication, Long> {

    PresentApplication save(PresentApplication app);

}
