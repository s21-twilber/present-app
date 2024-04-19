package org.example.service.impl;

import lombok.Data;
import org.example.entity.Status;
import org.example.enums.RolesEnum;
import org.example.enums.StatusesEnum;
import org.example.repository.StatusRepository;
import org.example.service.StatusService;
import org.springframework.stereotype.Service;

@Data
@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    @Override
    public Status getStatus(String status) {
        return statusRepository.findStatusByName(StatusesEnum.valueOf(status)).get();
    }
}
