package org.example.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.enums.StatusesEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
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

    @Transient
    private String commentChildren;

    @Column(name = "files_ref")
    @ElementCollection(targetClass=String.class)
    private Set<String> filesRef = null;

    @Column(name = "final_photo")
    private String finalPhoto;

    @Column(name = "coordinator_id")
    private Long coordinatorId;

    @Column(name = "accountant_id")
    private Long accountantId;

    @Column(name = "app_date")
    private String appDate;

    @Enumerated(EnumType.STRING)
    private StatusesEnum status;

}
