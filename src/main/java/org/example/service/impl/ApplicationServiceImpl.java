package org.example.service.impl;

import org.example.dto.ApplicationDto;
import org.example.dto.NewApplication;
import org.example.entity.PresentApplication;
import org.example.entity.Status;
import org.example.entity.User;
import org.example.enums.RolesEnum;
import org.example.enums.StatusesEnum;
import org.example.repository.ApplicationRepository;
import org.example.service.ApplicationService;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserService userService;
//    private final RegistryServiceImpl registryService;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, UserService userService) {
        this.applicationRepository = applicationRepository;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> createNewApplication(NewApplication app, Long userId) {
        PresentApplication tmp = new PresentApplication();
        User user = userService.findById(userId).get();
        tmp.setEmpId(user.getId());
        tmp.setEmail(user.getEmail());
        tmp.setFullName(user.getFullName());
        tmp.setBirthDate(user.getBirthDate());
        tmp.setPhoneNumber(app.getPhoneNumber());
        tmp.setNumChildren(app.getNumChildren());
        Random rand = new Random();
        List<User> coordinators = userService.findByRoleCoordinator(RolesEnum.ROLE_COORDINATOR);
        User responsible = coordinators.get(rand.nextInt(coordinators.size()));
        //
        tmp.setResponsibleId(responsible.getId());
        PresentApplication present = applicationRepository.save(tmp);
         return ResponseEntity.ok(new ApplicationDto(present.getId(), present.getResponsibleId()));
    }

    @Override
    public void updateStatusApplication(Long id, StatusesEnum statusesEnum) {
        Optional<PresentApplication> app = applicationRepository.findById(id);
        Status status = new Status(statusesEnum);
        app.get().setStatus(status);
        applicationRepository.save(app.get());
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
