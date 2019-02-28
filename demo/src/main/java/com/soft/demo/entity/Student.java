package com.soft.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String firstname;

    @NonNull
    private Integer age;

    @ManyToMany
    @JoinTable(name = "course_student",
            joinColumns = @JoinColumn(name="student_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="course_id", referencedColumnName="id"))
    private Set<Course> courseSet;
}
