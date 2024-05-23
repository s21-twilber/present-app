package org.example.util;

import org.example.dto.PresentDto;
import org.example.dto.RegistrationUserDto;
import org.example.entity.Present;
import org.example.entity.User;
import org.example.view.PresentView;
import org.example.view.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {

    // present view
    public PresentView mapToPresentView(Present present) {
        return PresentView.builder()
                .email(present.getEmployee().getEmail())
                .fullName(present.getEmployee().getFullName())
                .birthDate(present.getEmployee().getBirthDate())
                .phoneNumber(present.getEmployee().getPhoneNumber())
                .position(present.getEmployee().getPosition())
                .department(present.getEmployee().getDepartment())
                .employeeDate(present.getEmployee().getEmployeeDate())
                .appDate(present.getAppDate())
                .numChildren(present.getNumChildren())
                .commentChildren(present.getCommentChildren())
                .filesRef(present.getFilesRef())
                .finalPhoto(present.getFinalPhoto())
                .status(present.getStatus().name())
                .build();
    }

    // dto present
    public Present mapToPresentEntity(PresentDto presentDto) {
        return Present.builder()
                .numChildren(presentDto.getNumChildren())
                .fullChildName(presentDto.getFullChildName())
                .commentChildren(presentDto.getCommentChildren())
//                .filesRef(presentDto.getFile())
                .build();
    }

//    // user response
//    public UserResponse mapToUserResponse(User user) {
//        UserResponse userResponse = new UserResponse();
//        return userResponse;
//    }
//
//    // registration user
//    public User mapToUserEntity(RegistrationUserDto registrationUserDto) {
//        User user = new User();
//        return user;
//    }
}