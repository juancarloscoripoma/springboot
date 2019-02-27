package com.soft.demo.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class PhoneDTO implements Serializable {

    private Long id;

    private String type;

    private Integer areacode;

    private Integer number;
}
