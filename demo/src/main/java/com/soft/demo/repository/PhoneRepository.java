package com.soft.demo.repository;

import com.soft.demo.entity.Phone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

    @Query(value = "SELECT DISTINCT p FROM Phone p join fetch p.employee e where e.id = ?1",
            countQuery = "SELECT count (distinct p) FROM Phone p join p.employee e where e.id = ?1")
    Page<Phone> findPhoneById(Pageable pageable, Long id);
}
