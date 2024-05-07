package org.example.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.enums.StatusesEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Data
@Table(name = "present", schema = "app")
public class Present {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User employee;
    @Column(name = "num_children")
    private int numChildren;
    @Transient
    private String commentChildren;
    @Column(name = "files_ref")
    @ElementCollection(targetClass=String.class)
    private List<String> filesRef;
    @Transient
    private String finalPhoto;
    @Column(name = "coordinator_id")
    private Long coordinatorId;
    @Column(name = "accountant_id")
    private Long accountantId;
    @Enumerated(EnumType.STRING)
    private StatusesEnum status;


}
