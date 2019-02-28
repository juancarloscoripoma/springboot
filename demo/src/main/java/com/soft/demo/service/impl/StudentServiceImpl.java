package com.soft.demo.service.impl;

import com.soft.demo.entity.Course;
import com.soft.demo.entity.Student;
import com.soft.demo.repository.CourseRepository;
import com.soft.demo.repository.StudentRepository;
import com.soft.demo.rest.util.ParseBigDecimalObject;
import com.soft.demo.service.StudentService;
import com.soft.demo.service.dto.NativeDTO;
import com.soft.demo.service.dto.StudentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

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

        for (Course course : courseSet) {
            courseRepository.save(course);
        }
        studentDTO.setId(student.getId());
        return studentDTO;
    }

    @Override
    public List<NativeDTO> getAllSum(Long studentId) {
        List<Object[]> sumStudent = studentRepository.findMultipleById(studentId);

        List<Object[]> courses = studentRepository.findStudentAndCourseById(studentId);
        List<NativeDTO> nativeDTOS = new ArrayList<>();
        NativeDTO nativeDto = new NativeDTO();

        ParseBigDecimalObject parseBigDecimalObject = new ParseBigDecimalObject();
        for (Object obj : sumStudent) {
            nativeDto = new NativeDTO(new Long(0), "", 0, new Long(0),  parseBigDecimalObject.parseObject(obj));
            nativeDTOS.add(nativeDto);
        }

        for (Object[] obj : courses) {
            nativeDto = new NativeDTO(new Long(obj[0].toString()), obj[1].toString(), Integer.parseInt(obj[2].toString()), new Long(obj[3].toString()), new BigDecimal(0));
            nativeDTOS.add(nativeDto);
        }

        return nativeDTOS;
    }

}
