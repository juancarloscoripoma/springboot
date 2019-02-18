package com.soft.onetoonedb.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
public class UsersDTO {
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String addressLine1;
}