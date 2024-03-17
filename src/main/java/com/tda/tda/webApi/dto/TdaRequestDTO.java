package com.tda.tda.webApi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TdaRequestDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String job;

    private String education;

    private String jobE;
}
