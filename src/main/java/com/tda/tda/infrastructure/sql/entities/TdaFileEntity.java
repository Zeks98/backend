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
    private UUID id;

    @Lob
    @Column(name = "First_name")
    private String FirstName;

    @Column (name="Last_Name")
    private String LastName;

    @Column (name = "Job")
    private String job;

    @Column(name = "Education")
    private String education;

    @Column (name = "Job_Education")
    private String job_e;

}
