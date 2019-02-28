package com.soft.demo.service.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class NativeDTO {

    @NonNull
    private Long id;

    @NonNull
    private String firstname;

    @NonNull
    private Integer age;

    @NonNull
    private Long course_id;

    @NonNull
    private BigDecimal multiple;
}
