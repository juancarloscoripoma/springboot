package com.soft.demo.repository;

import com.soft.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
