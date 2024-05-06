package org.example.service;

import org.example.dto.PresentDto;
import org.example.entity.Present;
import org.example.view.PresentView;

import java.util.List;


public interface PresentService {

    Present createNewPresent(PresentDto app, Long userId);

    void updateStatusPresent(Long id, String statusName);

    void updateResponsiblePresent(Long id, Long coordId, Long accId);

    void deletePresent(Long id);

    List<PresentView> getAllPresents();

    List<PresentView> getUserPresents(Long userId);

    List<PresentView> getResponsiblePresents(Long userId);

    Present getUserPresent(Long appId);

    void findResponsibles(Present present);
}
