package org.example.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.enums.StatusesEnum;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Data
@Table(name = "present_application", schema = "app")
public class PresentApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "emp_id")
    private Long employeeId;
    @Column(name = "emp_email")
    private String email;
    @Column(name = "emp_name")
    private String fullName;
    @Column(name = "emp_date_of_birth")
    private String birthDate;
    @Column(name = "emp_tel_number")
    private String phoneNumber;
    @Transient
    private String position;
    @Transient
    private String employeeDate;
    @Column(name = "num_children")
    private int numChildren;
    @Transient
    private String commentChildren;
    @Transient
    private String filesRef;
    @Transient
    private String finalPhoto;
    @Column(name = "responsible_id")
    private Long responsibleId;

    @Enumerated(EnumType.STRING)
    private StatusesEnum status;


    @Override
    public String toString() {
        return "{id = " + id + ",\n" +
                " date of birth = " + birthDate + ",\n" +
                " phone number = " + phoneNumber + ",\n" +
                " position = " + position + ",\n" +
                " date of emplacement = " + employeeDate + ",\n" +
                " number of children = " + numChildren + ",\n" +
                " files = " + filesRef + ",\n" +
                " comment = " + commentChildren + ",\n" +
                " status = " + status.name() + ",\n" +
                " photo = " + finalPhoto +
                "},";
    }

}
