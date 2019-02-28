package com.soft.demo.rest;

import com.soft.demo.rest.util.HeaderUtil;
import com.soft.demo.rest.util.ResponseUtil;
import com.soft.demo.service.StudentService;
import com.soft.demo.service.dto.NativeDTO;
import com.soft.demo.service.dto.StudentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentResource {

    private final Logger log = LoggerFactory.getLogger(StudentResource.class);
    private static final String ENTITY_NAME = "student";
    private final StudentService studentService;

    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO) throws URISyntaxException {
        log.debug("REST request to save StudentDTO : {}", studentDTO);
        if (studentDTO.getId() != null && studentDTO.getId() != 0) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new student cannot already have an ID")).body(null);
        }
        StudentDTO result = studentService.save(studentDTO);
        return ResponseEntity.created(new URI("/api/students/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<NativeDTO>> getAllStudentsOptions(@PathVariable Long id) {
        log.debug("REST request to get a page of students");
        List<NativeDTO> students = studentService.getAllSum(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(students));
    }

}
