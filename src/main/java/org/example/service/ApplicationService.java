package org.example.service;

import org.example.dto.NewApplication;
import org.example.enums.StatusesEnum;
import org.springframework.http.ResponseEntity;


public interface ApplicationService {

    ResponseEntity<?> createNewApplication(NewApplication app);

    void updateStatusApplication(Long id, StatusesEnum statusesEnum);
}
