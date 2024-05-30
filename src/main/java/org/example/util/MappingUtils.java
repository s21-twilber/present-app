package org.example.util;

import org.example.dto.PresentDto;
import org.example.entity.Present;
import org.example.entity.User;
import org.example.view.PresentView;
import org.example.dto.UserDto;
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
                .fullChildName(present.getFullChildName())
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

    // user response
    public UserDto mapToUserResponse(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .birthDate(user.getBirthDate())
                .employeeDate(user.getEmployeeDate())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .position(user.getPosition())
                .department(user.getDepartment())
                .address(user.getAddress())
                .role(user.getRole().getName().name())
                .build();
    }
//
//    // registration user
//    public User mapToUserEntity(RegistrationUserDto registrationUserDto) {
//        User user = new User();
//        return user;
//    }
}
