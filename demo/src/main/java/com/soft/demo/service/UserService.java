package com.soft.demo.service;

import com.soft.demo.service.dto.UsersDTO;
import org.springframework.data.domain.Page;

public interface UserService {
    UsersDTO save(UsersDTO usersDTO);

    Page<UsersDTO> findAll(String orderBy, String direction, int page, int size);

    UsersDTO findOne(Long id);

    void delete(Long id);
}
