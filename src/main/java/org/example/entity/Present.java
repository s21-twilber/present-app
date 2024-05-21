package org.example.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.enums.StatusesEnum;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@Data
@Table(name = "present", schema = "app")
public class Present {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User employee;

    @Column(name = "num_children")
    private Integer numChildren;

    @Column(name = "child_name")
    private String fullName;

    @Transient
    private String commentChildren;

    @Column(name = "files_ref")
    @ElementCollection(targetClass=String.class)
    private Set<String> filesRef;

    @Column(name = "final_photo")
    private String finalPhoto;

    @Column(name = "coordinator_id")
    private Long coordinatorId;

    @Column(name = "accountant_id")
    private Long accountantId;

    @Column(name = "app_date")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date appDate;

    @Enumerated(EnumType.STRING)
    private StatusesEnum status;

    @Column(name = "comment_status")
    private String commentStatus;

}
