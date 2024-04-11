package org.example.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@NoArgsConstructor
@Data
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
    @Column(name = "role")
    private String role;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles", schema = "app",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles = new HashSet<>();


    //  Roles
    public Collection<Role> getRoles() {
        return roles;
    }
    public void setRoles(Collection<Role> roles) {
        this.role = roles.stream().findFirst().get().getName().name();
        this.roles = roles;
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
                "roles = " + role + "\n" +
                "},";
    }
}

