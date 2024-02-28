package com.tda.tda.infrastructure.sql.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tda_file")
public class TdaFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "first_name")
    private String firstName;

    @Column (name="last_Name")
    private String lastName;

    @Column (name = "job")
    private String job;

    @Column(name = "education")
    private String education;

    @Column (name = "job_education")
    private String jobE;

    @Column(name = "file_id")
    private Long fileId;

}
