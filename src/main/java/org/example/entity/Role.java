package org.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.RolesEnum;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;


@Entity
@NoArgsConstructor
@Data
@Table(name = "roles", schema = "app")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private RolesEnum name;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @Override
    public String getAuthority() {
        return this.name.name();
    }
}
