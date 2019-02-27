package com.soft.demo.service.dto;

import com.soft.demo.entity.Phone;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class EmployeeDTO implements Serializable {

    @NonNull
    private Long id;

    @NonNull
    private String firstname;

    @NonNull
    private String lastname;

    @NonNull
    private BigDecimal salary;

    private List<Phone> phone;
}
