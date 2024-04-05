package org.example.entity;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public abstract class Application {
    private Long id;
    private Long empId;
    private String email;
    private String fullName;
    private String birthDate;
    private String phoneNumber;
    private String position;
    private String empDate;
    private String filesRef;


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


    //  Files
    public String getFilesRef() {
        return filesRef;
    }
    public void setFilesRef(String filesRef) {
        this.filesRef = filesRef;
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
                " emp id = " + empId + ",\n" +
                " emp name = " + fullName + ",\n" +
                " date of birth = " + birthDate + ",\n" +
                " phone number = " + phoneNumber + ",\n" +
                " position = " + position + ",\n" +
                " date of emplacement = " + empDate + ",\n" +
                " files = " + filesRef +
                "},";
    }
}
