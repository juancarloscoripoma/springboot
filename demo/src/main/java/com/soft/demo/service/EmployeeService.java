package com.soft.demo.service;

import com.soft.demo.service.dto.EmployeeDTO;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    EmployeeDTO save(EmployeeDTO employeeDTO);
    Page<EmployeeDTO> findAll(String orderBy, String direction, int page, int size);
}
