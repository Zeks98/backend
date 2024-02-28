package com.tda.tda.infrastructure.sql.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Length;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tda")
public class TdaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_content", length = Length.LONG32)
    private String fileContent;



}
