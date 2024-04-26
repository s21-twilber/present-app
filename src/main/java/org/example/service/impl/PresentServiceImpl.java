package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.PresentDto;
import org.example.entity.Present;
import org.example.entity.User;
import org.example.enums.RolesEnum;
import org.example.enums.StatusesEnum;
import org.example.exception.NotFoundException;
import org.example.repository.PresentRepository;
import org.example.service.PresentService;
import org.example.service.RoleService;
import org.example.service.UserService;
import org.example.view.PresentView;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class PresentServiceImpl implements PresentService {

    private final PresentRepository repository;
    private final UserService userService;
    private final RoleService roleService;

    public PresentServiceImpl(PresentRepository repository, UserService userService, RoleService roleService) {
        this.repository = repository;
        this.userService = userService;
        this.roleService = roleService;
    }


    @Override
    public Present createNewPresent(PresentDto presentDto, Long userId) {
        Present tmp = new Present();
            User user = userService.findById(userId);
            tmp.setEmployee(user);
            tmp.setNumChildren(presentDto.getNumChildren());
            tmp.setFilesRef(presentDto.getFilesRef());
            tmp.setStatus(StatusesEnum.UNDER_CONSIDERATION);
            Random rand = new Random();
            List<User> coordinators = userService.findByRoleCoordinator(RolesEnum.ROLE_COORDINATOR);
            User responsible = coordinators.get(rand.nextInt(coordinators.size()));
            tmp.setResponsibleId(responsible.getId());
            Present present = repository.save(tmp);
            log.info("Create new present application id = {}", present.getId());
            return present;
    }

    @Override
    public void updateStatusPresent(Long id, String statusName) {
            Present app = repository.findById(id).orElseThrow(() ->
                    new NotFoundException("Application not found"));
            if (app.getStatus() != StatusesEnum.RECEIVED) {
                app.setStatus(StatusesEnum.valueOf(statusName));
                repository.save(app);
                log.info("Update status of present application {}", app.getStatus());
            }
    }

    @Override
    public void deletePresent(Long id) {
        repository.deletePresentById(id);
    }

    @Override
    public List<PresentView> getRepository(Long userId) {
        List<Present> list = repository.findAllByEmployee_Id(userId);
        List<PresentView> res = new ArrayList<>();
        for(Present l : list) {
            PresentView view = new PresentView(l);
            res.add(view);
        }
        return res;

    }

    @Override
    public List<Present> getCoordinatorPresents(Long userId) {
        if (userService.findById(userId).getRole() == roleService.getCoordinatorRole()) {
            return repository.findAllByResponsibleIdAndStatus(userId, StatusesEnum.UNDER_CONSIDERATION);
        }
        return repository.findAllByResponsibleIdAndStatus(userId, StatusesEnum.APPROVED_BY_COORDINATOR);
    }


    @Override
    public Present getUserPresent(Long appId) {
        return repository.findById(appId).orElseThrow(() ->
                new NotFoundException("Application not found"));
    }


}
