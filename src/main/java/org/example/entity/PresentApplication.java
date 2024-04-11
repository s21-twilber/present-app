package org.example.entity;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Data
@Table(name = "present_application", schema = "app")
public class PresentApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private Long empId;
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
    private String empDate;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "applications_statuses", schema = "app",
            joinColumns = @JoinColumn(name = "app_id"),
            inverseJoinColumns = @JoinColumn(name = "status_id"))
    private Status status;


    @Override
    public String toString() {
        return "{id = " + id + ",\n" +
                " date of birth = " + birthDate + ",\n" +
                " phone number = " + phoneNumber + ",\n" +
                " position = " + position + ",\n" +
                " date of emplacement = " + empDate + ",\n" +
                " number of children = " + numChildren + ",\n" +
                " files = " + filesRef + ",\n" +
                " comment = " + commentChildren + ",\n" +
                " photo = " + finalPhoto +
                "},";
    }
}
