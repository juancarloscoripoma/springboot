package com.soft.demo.service.dto;

import com.soft.demo.entity.Employee;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class PhoneDTO implements Serializable {

    @NonNull
    private Long id;

    @NonNull
    private String type;

    @NonNull
    private Integer areacode;

    @NonNull
    private Integer number;
}
