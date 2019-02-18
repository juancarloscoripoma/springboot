package com.soft.onetoonedb.service;

import com.soft.onetoonedb.entity.Users;
import com.soft.onetoonedb.service.dto.UsersDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Users save(UsersDTO usersDTO);

    Page<Users> findAll(Pageable pageable);

    Users findOne(Long id);

    void delete(Long id);
}
