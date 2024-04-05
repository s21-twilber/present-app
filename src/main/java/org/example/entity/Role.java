package org.example.entity;

import org.example.enums.RolesEnum;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.UUID;

@Entity
@Table(name = "roles", schema = "app")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private RolesEnum name;

    public Role(){}

    public Role(Integer id, RolesEnum name){
        this.id = id;
        this.name = name;
    }

    //  Id
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    //  Name
    public RolesEnum getName() {
        return name;
    }
    public void setName(RolesEnum name) {
        this.name = name;
    }


    public String getAuthority() {
        return String.valueOf(getName());
    }
}
