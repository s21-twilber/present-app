package org.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.RolesEnum;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Data
@Table(name = "roles", schema = "app")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private RolesEnum name;


    public String getAuthority() {
        return String.valueOf(getName());
    }
}
