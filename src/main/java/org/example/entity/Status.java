package org.example.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.enums.StatusesEnum;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "statuses", schema = "app")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private StatusesEnum name;

    @Transient
    @OneToMany(mappedBy = "statuses")
    private Set<PresentApplication> present;


    public Status(StatusesEnum name) {
        this.name = name;
    }


}
