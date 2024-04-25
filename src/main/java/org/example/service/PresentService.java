package org.example.service;

import org.example.dto.PresentDto;
import org.example.entity.Present;

import java.util.List;


public interface PresentService {

    Present createNewPresent(PresentDto app, Long userId);

    void updateStatusPresent(Long id, String statusName);

    void deletePresent(Long id);

    List<Present> getRepository(Long userId);

    List<Present> getCoordinatorPresents(Long userId);

    Present getUserPresent(Long appId);
}
