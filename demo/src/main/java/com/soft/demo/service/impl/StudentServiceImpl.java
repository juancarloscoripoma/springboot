package com.soft.demo.service.impl;

import com.soft.demo.entity.Course;
import com.soft.demo.entity.Student;
import com.soft.demo.repository.CourseRepository;
import com.soft.demo.repository.StudentRepository;
import com.soft.demo.service.StudentService;
import com.soft.demo.service.dto.StudentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public StudentDTO save(StudentDTO studentDTO) {
        log.debug("Request to save Student : {}", studentDTO);
        Set<Course> courseSet = new HashSet<>();

        Student student = new Student(studentDTO.getFirstname(), studentDTO.getAge());

        studentDTO.getCourseSet().forEach(cours -> {
            Course course = new Course(cours.getCode());
            courseSet.add(course);
        });
        student.setCourseSet(courseSet);
        studentRepository.save(student);

        courseSet.forEach(course -> {
            courseRepository.save(course);
        });
        studentDTO.setId(student.getId());
        return studentDTO;
    }
}
