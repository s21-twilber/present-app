package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.PresentDto;
import org.example.entity.Present;
import org.example.entity.User;
import org.example.enums.RolesEnum;
import org.example.enums.StatusesEnum;
import org.example.exception.NotFoundException;
import org.example.repository.PresentRepository;
import org.example.service.FileService;
import org.example.service.PresentService;
import org.example.service.RoleService;
import org.example.service.UserService;
import org.example.view.PresentView;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class PresentServiceImpl implements PresentService {

    private final PresentRepository repository;
    private final UserService userService;
    private final RoleService roleService;
    private final FileService fileService;

    public PresentServiceImpl(PresentRepository repository, UserService userService, RoleService roleService, FileService fileService) {
        this.repository = repository;
        this.userService = userService;
        this.roleService = roleService;
        this.fileService = fileService;
    }


    @Override
    public Present createNewPresent(PresentDto presentDto, Long userId) throws IOException {
        Present tmp = new Present();
            User user = userService.findById(userId);
            tmp.setEmployee(user);
            tmp.setNumChildren(presentDto.getNumChildren());
            tmp.setStatus(StatusesEnum.UNDER_CONSIDERATION);
            tmp.setFilesRef(fileService.upload(presentDto.getFile()));
            findResponsibles(tmp);
            Present present = repository.save(tmp);
            log.info("Create new present application id = {}", present.getId());
            return present;
    }

    @Override
    public Present updatePresent(PresentDto presentDto, Long presentId) throws IOException {
        Present tmp = repository.findById(presentId).get();
        tmp.setNumChildren(presentDto.getNumChildren());
        tmp.setStatus(StatusesEnum.UNDER_CONSIDERATION);
        tmp.setFilesRef(fileService.upload(presentDto.getFile()));
        Present present = repository.save(tmp);
        log.info("Update present application id = {}", present.getId());
        return present;
    }

    @Override
    public void updateStatusPresent(Long id, String statusName) {
            Present present = repository.findById(id).orElseThrow(() ->
                    new NotFoundException("Application not found"));
            if ((present.getStatus() != StatusesEnum.RECEIVED) ||
                    (present.getStatus() != StatusesEnum.GRANTED)) {
                present.setStatus(StatusesEnum.valueOf(statusName));
                repository.save(present);
                log.info("Update status of present application {}", present.getStatus());
            }
    }

    @Override
    public void addStatusComment(Long id, String comment) {
        Present present = repository.findById(id).orElseThrow(() ->
                new NotFoundException("Application not found"));
        if (present.getStatus() != StatusesEnum.GRANTED) {
            present.setCommentStatus(comment);
            repository.save(present);
            log.info("Update comment of present application {}", present.getCommentStatus());
        }
    }

    @Override
    public void updateFinalPhotoPresent(Long id, MultipartFile file) throws IOException {
        Present present = repository.findById(id).orElseThrow(() ->
                new NotFoundException("Application not found"));
        if (present.getStatus() == StatusesEnum.GRANTED) {
            present.setStatus(StatusesEnum.RECEIVED);
            present.setFinalPhoto(fileService.upload(file));
            repository.save(present);
            log.info("Update status of present application {}", present.getStatus());
        }
    }

    @Override
    public void updateResponsiblePresent(Long id, Long coordId, Long accId) {
        Present present = repository.findById(id).orElseThrow(() ->
                new NotFoundException("Application not found"));
        present.setCoordinatorId(coordId);
        present.setAccountantId(accId);
        repository.save(present);
        log.info("Update responsible id of present application {}", present.getStatus());
    }

    @Override
    public void deletePresent(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<PresentView> getAllPresents() {
        List<Present> list = repository.findAll();
        List<PresentView> res = new ArrayList<>();
        for(Present l : list) {
            PresentView view = new PresentView(l);
            res.add(view);
        }
        return res;
    }

    @Override
    public List<PresentView> getUserPresents(Long userId) {
        List<Present> list = repository.findAllByEmployee_Id(userId);
        List<PresentView> res = new ArrayList<>();
        for(Present l : list) {
            PresentView view = new PresentView(l);
            res.add(view);
        }
        return res;

    }

    @Override
    public List<PresentView> getResponsiblePresents(Long userId) {
        List<Present> list = repository.findAllByCoordinatorIdAndStatusIsNot(userId, StatusesEnum.DRAFT);
        if (userService.findById(userId).getRole() == roleService.getRole(RolesEnum.ROLE_ACCOUNTANT)) {
            list = repository.findAllByAccountantIdAndStatus(userId, StatusesEnum.APPROVED_BY_COORDINATOR);
        }
        List<PresentView> res = new ArrayList<>();
        for(Present l : list) {
            PresentView view = new PresentView(l);
            res.add(view);
        }
        return res;
    }


    @Override
    public Present getUserPresent(Long appId) {
        return repository.findById(appId).orElseThrow(() ->
                new NotFoundException("Application not found"));
    }

    @Override
    public void findResponsibles(Present present) {
        Random rand = new Random();
        List<User> coordinators = userService.findByRole(RolesEnum.ROLE_COORDINATOR);
        User coordinator = coordinators.get(rand.nextInt(coordinators.size()));
        present.setCoordinatorId(coordinator.getId());
        List<User> accountants = userService.findByRole(RolesEnum.ROLE_ACCOUNTANT);
        User accountant = accountants.get(rand.nextInt(accountants.size()));
        present.setAccountantId(accountant.getId());
    }




}
