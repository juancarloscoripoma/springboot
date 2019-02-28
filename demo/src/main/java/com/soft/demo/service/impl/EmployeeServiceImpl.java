package com.soft.demo.service.impl;

import com.soft.demo.entity.Employee;
import com.soft.demo.entity.Phone;
import com.soft.demo.repository.EmployeeRepository;
import com.soft.demo.repository.PhoneRepository;
import com.soft.demo.service.EmployeeService;
import com.soft.demo.service.dto.EmployeeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;
    private final PhoneRepository phoneRepository;

    private Sort.Direction sort = Sort.Direction.ASC;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PhoneRepository phoneRepository) {
        this.employeeRepository = employeeRepository;
        this.phoneRepository = phoneRepository;
    }

    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        log.debug("Request to save Employee : {}", employeeDTO);
        List<Phone> result = new ArrayList<>();
        List<Phone> auxPhone = new ArrayList<>();

        Employee employee = new Employee(employeeDTO.getFirstname(),employeeDTO.getLastname(),employeeDTO.getSalary());

        Employee finalEmployee = employee;
        employeeDTO.getPhone().forEach(pho -> {
            Phone phone = new Phone(pho.getType(), pho.getAreacode(), pho.getNumber(), finalEmployee);
            result.add(phone);
        });

        employee = employeeRepository.save(employee);
        result.forEach((phone -> {
            phone = phoneRepository.save(phone);
            auxPhone.add(phone);
        }));

        employeeDTO.setId(employee.getId());
        employeeDTO.setPhone(auxPhone);
        return employeeDTO;
    }

    @Override
    public Page<EmployeeDTO> findAll(String orderBy, String direction, int page, int size) {
        log.debug("Request to get all Employees");
        if (direction.equalsIgnoreCase("DESC")) {
            sort = Sort.Direction.DESC;
        }
        Pageable pageable = new PageRequest(page, size, sort, orderBy);

        Page<Employee> result = employeeRepository.findAll(pageable);
        return result.map(emp -> new EmployeeDTO(emp.getId(), emp.getFirstname(), emp.getLastname(), emp.getSalary()));
    }
}
