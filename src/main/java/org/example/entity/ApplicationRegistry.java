package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.enums.StatusesEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@AllArgsConstructor
@Data
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
    @Column(name = "app_type")
    private String type;
    @Column(name = "status")
    private StatusesEnum status;
    private Long responsibleId;

    public ApplicationRegistry(Long applicationId, Long empId, StatusesEnum status, Long responsibleId) {

        this.applicationId = applicationId;
        this.empId = empId;
        this.status = status;
        this.responsibleId = responsibleId;
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
