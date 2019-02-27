package com.soft.demo.service.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class UsersDTO {

    @NonNull
    private Long id;

    @NonNull
    private String firstname;

    private AddressDTO addressDTO;
}
