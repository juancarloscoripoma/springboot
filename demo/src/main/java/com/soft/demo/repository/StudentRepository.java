package com.soft.demo.repository;

import com.soft.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("unused")
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "select sum( cs.course_id*cs.student_id ) mult from student st "+
            "inner join course_student cs on st.id=cs.student_id "+
            "where st.id<=?1",
            nativeQuery = true)
    List<Object[]> findMultipleById(Long studentId);

    @Query(value = "select id, firstname,age,course_id from student st "+
            "inner join course_student cs on st.id=cs.student_id where st.id=?1",
            nativeQuery = true)
    List<Object[]> findStudentAndCourseById(Long studentId);
}
