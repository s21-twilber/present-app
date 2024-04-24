package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.PresentDto;
import org.example.entity.PresentApplication;
import org.example.entity.Status;
import org.example.entity.User;
import org.example.enums.RolesEnum;
import org.example.exception.AppError;
import org.example.exception.NotFoundException;
import org.example.repository.ApplicationRepository;
import org.example.service.ApplicationService;
import org.example.service.StatusService;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserService userService;
    private final StatusService statusService;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, UserService userService, StatusService statusService) {
        this.applicationRepository = applicationRepository;
        this.userService = userService;
        this.statusService = statusService;
    }

    @Override
    public PresentApplication createNewApplication(PresentDto presentDto, Long userId) {
        PresentApplication tmp = new PresentApplication();
            User user = userService.findById(userId);
            tmp.setEmployeeId(user.getId());
            tmp.setEmail(user.getEmail());
            tmp.setFullName(user.getFullName());
            tmp.setBirthDate(user.getBirthDate());
            tmp.setPhoneNumber(presentDto.getPhoneNumber());
            tmp.setNumChildren(presentDto.getNumChildren());
            Random rand = new Random();
            List<User> coordinators = userService.findByRoleCoordinator(RolesEnum.ROLE_COORDINATOR);
            User responsible = coordinators.get(rand.nextInt(coordinators.size()));
            tmp.setResponsibleId(responsible.getId());
            PresentApplication present = applicationRepository.save(tmp);
            log.info("Create new present application id = {}", present.getId());
            return present;
    }

    @Override
    public void updateStatusApplication(Long id, String statusName) {
            PresentApplication app = applicationRepository.findById(id).orElseThrow(() ->
                    new NotFoundException("Заявка не найдена"));
            Status status = statusService.getStatus(statusName);
            app.setStatus(status);
            applicationRepository.save(app);
            log.info("Update status of present application {}", app.getStatus());
    }

    @Override
    public void deleteApplication(Long id) {
        applicationRepository.deletePresentApplicationById(id);
    }

    @Override
    public List<PresentApplication> getRepository(Long userId) {
        return applicationRepository.findAllByEmployeeId(userId);
    }

    @Override
    public List<PresentApplication> getCoordinatorApplications(Long userId) {
        return applicationRepository.findAllByResponsibleId(userId);
    }

    @Override
    public PresentApplication getUserApplication(Long userId, Long appId) {
        return applicationRepository.findByEmployeeIdAndId(userId, appId).orElseThrow(() ->
                new NotFoundException("Заявка не найдена"));
    }

    @Override
    public PresentApplication getUserApplication(Long appId) {
        return applicationRepository.findById(appId).get();
    }


}
