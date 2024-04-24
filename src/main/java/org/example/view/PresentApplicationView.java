package org.example.view;


import lombok.Data;
import org.example.entity.PresentApplication;


@Data
public class PresentApplicationView {

    private String email;
    private String fullName;
    private String birthDate;
    private String phoneNumber;
    private String position;
    private String employeeDate;
    private int numChildren;
    private String commentChildren;
    private String filesRef;
    private String finalPhoto;
    private Long responsibleId;
    private String status;

    public PresentApplicationView(PresentApplication application) {
        this.email = application.getEmail();
        this.fullName = application.getFullName();
        this.birthDate = application.getBirthDate();
        this.phoneNumber = application.getPhoneNumber();
        this.position = application.getPosition();
        this.employeeDate = application.getEmployeeDate();
        this.numChildren = application.getNumChildren();
        this.commentChildren = application.getCommentChildren();
        this.filesRef = application.getFilesRef();
        this.finalPhoto = application.getFinalPhoto();
        this.responsibleId = application.getResponsibleId();
        System.out.println(application.getStatus());
        this.status = application.getStatus().name();
    }
}
