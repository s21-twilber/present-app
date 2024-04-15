package org.example.service;

import org.example.dto.NewApplication;
import org.example.enums.StatusesEnum;
import org.springframework.http.ResponseEntity;


public interface ApplicationService {

    ResponseEntity<?> createNewApplication(NewApplication app, Long userId);

    void updateStatusApplication(Long id, StatusesEnum statusesEnum);

    ResponseEntity<?> getRepository(Long userId);

    ResponseEntity<?> getCoordinatorRepository(Long userId);

    ResponseEntity<?> getUserApplication(Long userId, Long appId);

    ResponseEntity<?> getUserApplication(Long appId);
}
