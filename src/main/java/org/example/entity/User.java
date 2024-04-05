package org.example.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "users", schema = "app")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String fullName;
    @Column(name = "date_of_birth")
    private String birthDate;
    @Column(name = "tel_number")
    private String phoneNumber;
    @Transient
    private String position;
    @Transient
    private String empDate;
    @Transient
    private String role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", schema = "app",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles = new HashSet<>();


    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(long id, String email, String fullName) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
    }

    public User(){}

    public User(String fullName, String birthDate, String email, String phoneNumber, String position, String empDate){
        this.email = email;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.empDate = empDate;
    }

    public User(Long id, String fullName, String birthDate, String email, String phoneNumber, String position,
                String empDate, String role){
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.empDate = empDate;
        this.role = role;
    }

    public User(Long id, String fullName, String password, String birthDate, String email, String phoneNumber, String position,
                String empDate, String role){
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.empDate = empDate;
        this.role = role;
    }

    public User(String email) {
        this.email = email;
    }


    //  Id
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    //  Email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    //  Password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
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

    //  Role
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    //  Roles
    public Collection<Role> getRoles() {
        return roles;
    }
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
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
                " name = " + fullName + ",\n" +
                " date of birth = " + birthDate + ",\n" +
                " email = " + email + ",\n" +
                " phone number = " + phoneNumber + ",\n" +
                " position = " + position + ",\n" +
                " date of emplacement = " + empDate + ",\n" +
                " role = " + role +
                "},";
    }
}

