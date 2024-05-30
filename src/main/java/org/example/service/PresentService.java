package org.example.service;

import org.example.dto.PresentDto;
import org.example.entity.Present;
import org.example.view.PresentView;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface PresentService {

    Present createNewPresent(PresentDto app, Long userId) throws IOException;

    Present updatePresent(PresentDto presentDto, Long presentId) throws IOException;

    void updateStatusPresent(Long id, String statusName);

    void addStatusComment(Long id, String comment);

    void updateFinalPhotoPresent(Long id, MultipartFile file) throws IOException;

    void updateResponsiblePresent(Long id, Long coordId, Long accId);

    void deletePresent(Long id);

    List<PresentView> getAllPresents();

    List<PresentView> getUserPresents(Long userId);

    List<PresentView> getResponsiblePresents(Long userId);

    Present getUserPresent(Long appId);

    void findResponsible(Present present);
}
