package org.example.view;


import lombok.Data;
import org.example.entity.Present;


@Data
public class PresentView {

    private String email;
    private String fullName;
    private String birthDate;
    private String phoneNumber;
    private String position;
    private String department;
    private String employeeDate;
    private int numChildren;
    private String commentChildren;
    private String filesRef;
    private String finalPhoto;
    private Long coordinatorId;
    private Long accountantId;
    private String status;

    public PresentView(Present application) {
        this.email = application.getEmployee().getEmail();
        this.fullName = application.getEmployee().getFullName();
        this.birthDate = application.getEmployee().getBirthDate();
        this.phoneNumber = application.getEmployee().getPhoneNumber();
        this.position = application.getEmployee().getPosition();
        this.department = application.getEmployee().getDepartment();
        this.employeeDate = application.getEmployee().getEmployeeDate();
        this.numChildren = application.getNumChildren();
        this.commentChildren = application.getCommentChildren();
        this.filesRef = application.getFilesRef();
        this.finalPhoto = application.getFinalPhoto();
        this.coordinatorId = application.getCoordinatorId();
        this.accountantId = application.getAccountantId();
        this.status = application.getStatus().name();
    }
}
