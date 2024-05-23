package org.example.view;


import lombok.*;
import org.example.entity.Present;


import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PresentView {

    private String email;
    private String fullName;
    private String birthDate;
    private String phoneNumber;
    private String position;
    private String department;
    private String employeeDate;
    private Date appDate;
    private int numChildren;
    private String commentChildren;
    private Set<String> filesRef;
    private String finalPhoto;
//    private Long coordinatorId;
//    private Long accountantId;
    private String status;

}
