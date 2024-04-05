package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "present_application", schema = "app")
public class PresentApplication extends Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "emp_id")
    private Long empId;
    @Column(name = "emp_email")
    private String email;
    @Column(name = "emp_name")
    private String fullName;
    @Column(name = "emp_date_of_birth")
    private String birthDate;
    @Column(name = "emp_tel_number")
    private String phoneNumber;
    @Column(name = "emp_position")
    private String position;
    @Column(name = "emp_date")
    private String empDate;
    @Column(name = "num_children")
    private int numChildren;
    @Column(name = "comment_children")
    private String commentChildren;
    @Column(name = "files_ref")
    private String filesRef;
    @Column(name = "final_photo")
    private String finalPhoto;


    //  Id
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    //  Emp id
    public Long getEmpId() {
        return empId;
    }
    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    //  Email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    //  Full name
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    //  Birth date
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    //  Phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    //  Position
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    //  Emp date
    public String getEmpDate() {
        return empDate;
    }
    public void setEmpDate(String empDate) {
        this.empDate = empDate;
    }

    //  Num children
    public int getNumChildren() {
        return numChildren;
    }
    public void setNumChildren(int numChildren) {
        this.numChildren = numChildren;
    }

    //  Comment children
    public String getCommentChildren() {
        return commentChildren;
    }
    public void setCommentChildren(String commentChildren) {
        this.commentChildren = commentChildren;
    }

    //  Files
    public String getFilesRef() {
        return filesRef;
    }
    public void setFilesRef(String filesRef) {
        this.filesRef = filesRef;
    }

    //  Final photo
    public String getFinalPhoto() {
        return finalPhoto;
    }
    public void setFinalPhoto(String finalPhoto) {
        this.finalPhoto = finalPhoto;
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
