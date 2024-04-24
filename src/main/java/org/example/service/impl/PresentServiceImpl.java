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
import org.example.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class PresentServiceImpl implements PresentService {

    private final PresentRepository repository;
    private final UserService userService;

    public PresentServiceImpl(PresentRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }


    @Override
    public Present createNewPresent(PresentDto presentDto, Long userId) {
        Present tmp = new Present();
            User user = userService.findById(userId);
            tmp.setEmployeeId(user.getId());
            tmp.setEmail(user.getEmail());
            tmp.setFullName(user.getFullName());
            tmp.setBirthDate(user.getBirthDate());
            tmp.setPhoneNumber(presentDto.getPhoneNumber());
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
                    new NotFoundException("Заявка не найдена"));
        app.setStatus(StatusesEnum.valueOf(statusName));
            repository.save(app);
            log.info("Update status of present application {}", app.getStatus());
    }

    @Override
    public void deletePresent(Long id) {
        repository.deletePresentById(id);
    }

    @Override
    public List<Present> getRepository(Long userId) {
        return repository.findAllByEmployeeId(userId);
    }

    @Override
    public List<Present> getCoordinatorPresents(Long userId) {
        return repository.findAllByResponsibleId(userId);
    }

    @Override
    public Present getUserPresent(Long userId, Long appId) {
        System.out.println(repository.findById(appId).get().toString());

        return repository.findByEmployeeIdAndId(userId, appId).orElseThrow(() ->
                new NotFoundException("Заявка не найдена"));
    }

    @Override
    public Present getUserPresent(Long appId) {
        return repository.findById(appId).get();
    }


}
