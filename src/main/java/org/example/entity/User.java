package org.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@NoArgsConstructor
@Data
@Table(name = "users", schema = "app")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String email;
    @Column(name = "name")
    private String fullName;
    @Column(name = "date_of_birth")
    private String birthDate;
    @Column(name = "ph_number")
    private String phoneNumber;
    private String position;
    private String department;
    private String employeeDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_role", schema = "app",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Role role;



    @Override
    public String toString() {
        return "{id = " + id + ",\n" +
                " name = " + fullName + ",\n" +
                " date of birth = " + birthDate + ",\n" +
                " email = " + email + ",\n" +
                " phone number = " + phoneNumber + ",\n" +
                " position = " + position + ",\n" +
                " date of emplacement = " + employeeDate + ",\n" +
                "roles = " + role + "\n" +
                "},";
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

