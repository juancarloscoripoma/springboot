package com.soft.demo.repository;

import com.soft.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
}
