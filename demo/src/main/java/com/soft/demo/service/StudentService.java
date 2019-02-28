package com.soft.demo.service;

import com.soft.demo.service.dto.NativeDTO;
import com.soft.demo.service.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    StudentDTO save(StudentDTO studentDTO);
    List<NativeDTO> getAllSum(Long studentId);
}
