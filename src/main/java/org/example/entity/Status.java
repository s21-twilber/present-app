package org.example.entity;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.enums.StatusesEnum;

import javax.persistence.*;

@Entity
@Data
@Table(name = "statuses", schema = "app")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private StatusesEnum name;


    public Status(StatusesEnum name) {
        this.name = name;
    }


}
