package org.example.service;

import org.example.dto.PresentDto;
import org.example.enums.StatusesEnum;
import org.example.exception.AppError;
import org.springframework.http.ResponseEntity;


public interface ApplicationService {

    ResponseEntity<?> createNewApplication(PresentDto app, Long userId) throws AppError;

    void updateStatusApplication(Long id, String statusName) throws AppError;

    ResponseEntity<?> getRepository(Long userId);

    ResponseEntity<?> getCoordinatorRepository(Long userId);

    ResponseEntity<?> getUserApplication(Long userId, Long appId);

    ResponseEntity<?> getUserApplication(Long appId);
}
