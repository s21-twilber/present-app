package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.PresentDto;
import org.example.entity.PresentApplication;
import org.example.entity.Status;
import org.example.entity.User;
import org.example.enums.RolesEnum;
import org.example.exception.AppError;
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
    public ResponseEntity<?> createNewApplication(PresentDto presentDto, Long userId) throws AppError {
        PresentApplication tmp = new PresentApplication();
        try {
            User user = userService.findById(userId).get();
            tmp.setEmpId(user.getId());
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
            return ResponseEntity.ok(present);

        } catch (BadCredentialsException e) {
            throw new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Не найден пользователь");
        }
    }

    @Override
    public void updateStatusApplication(Long id, String statusName) throws AppError {
        try {
            Optional<PresentApplication> app = applicationRepository.findById(id);
            Status status = statusService.getStatus(statusName);
            app.get().setStatus(status);
            applicationRepository.save(app.get());
            log.info("Update status of present application {}", app.get().getStatus());
        } catch (BadCredentialsException e) {
            throw new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Не найдена заявка");
        }
    }

    @Override
    public ResponseEntity<?> getRepository(Long userId) {
        return ResponseEntity.ok(applicationRepository.findAllByEmpId(userId));
    }

    @Override
    public ResponseEntity<?> getCoordinatorRepository(Long userId) {
        return ResponseEntity.ok(applicationRepository.findAllByResponsibleId(userId));
    }

    @Override
    public ResponseEntity<?> getUserApplication(Long userId, Long appId) {
        return ResponseEntity.ok(applicationRepository.findByEmpIdAndId(userId, appId));
    }

    @Override
    public ResponseEntity<?> getUserApplication(Long appId) {
        return ResponseEntity.ok(applicationRepository.findById(appId));
    }


}
