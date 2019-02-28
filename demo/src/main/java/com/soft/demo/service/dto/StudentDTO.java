package com.soft.demo.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class StudentDTO {
    private Long id;
    private String firstname;
    private Integer age;
    private Set<CourseDTO> courseSet;

}
