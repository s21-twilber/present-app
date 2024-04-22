package org.example.service;

import org.example.dto.PresentDto;
import org.example.entity.PresentApplication;
import org.example.exception.AppError;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ApplicationService {

    PresentApplication createNewApplication(PresentDto app, Long userId);

    void updateStatusApplication(Long id, String statusName);

    List<PresentApplication> getRepository(Long userId);

    List<PresentApplication> getCoordinatorApplications(Long userId);

    PresentApplication getUserApplication(Long userId, Long appId);

    PresentApplication getUserApplication(Long appId);
}
