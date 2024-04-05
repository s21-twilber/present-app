package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "application_registry", schema = "app")
public class ApplicationRegistry {

    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "app_id")
    private Long applicationId;
    @Column(name = "emp_id")
    private Long empId;
//    private Application application;
    @Column(name = "app_type")
    private String type;
    @Column(name = "status")
    private String status;
    private String empEmail;
    private String applicationDate;
    private Long responsibleId;
    private String commentStatus;


    // Id
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    // ApplicationId
    public Long getApplicationId() {
        return applicationId;
    }
    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    // EmpId
    public Long getEmpId() {
        return empId;
    }
    public void setEmpId(Long empId) {
        this.empId = empId;
    }


    // Type
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    // Status
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    // Emp Email
    public String getEmpEmail() {
        return empEmail;
    }
    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    // ApplicationDate
    public String getApplicationDate() {
        return applicationDate;
    }
    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    // ResponsibleId
    public Long getResponsibleId() {
        return responsibleId;
    }
    public void setResponsibleId(Long responsibleId) {
        this.responsibleId = responsibleId;
    }

    // CommentStatus
    public String getCommentStatus() {
        return commentStatus;
    }
    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "{id = " + id + ",\n" +
                " application id = " + applicationId + ",\n" +
                " emp id = " + empId + ",\n" +
                " type = " + type + ",\n" +
                " status = " + status + ",\n" +
                " date of application = " + applicationDate + ",\n" +
                " responsible id = " + responsibleId + ",\n" +
                " comment = " + commentStatus +
                "},";
    }
}
