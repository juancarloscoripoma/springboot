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
        Employee employee = new Employee(employeeDTO.getFirstname(),employeeDTO.getLastname(),employeeDTO.getSalary());

        Phone phone = new Phone(employeeDTO.getPhone().getType(), employeeDTO.getPhone().getAreacode(), employeeDTO.getPhone().getNumber(), employee);

        employee = employeeRepository.save(employee);
        phone = phoneRepository.save(phone);


        employeeDTO.setId(employee.getId());
        employeeDTO.getPhone().setId(phone.getId());
        return employeeDTO;
    }

    @Override
    public Page<EmployeeDTO> findAll(String orderBy, String direction, int page, int size) {
        log.debug("Request to get all Employees");
        if(direction.equalsIgnoreCase("DESC")){
            sort = Sort.Direction.DESC;
        }
        Pageable pageable = new PageRequest(page, size, sort, orderBy);

        Page<Employee> result = employeeRepository.findAll(pageable);
        return result.map(emp -> new EmployeeDTO( emp.getId(), emp.getFirstname(), emp.getLastname(), emp.getSalary() ) );
    }
}
