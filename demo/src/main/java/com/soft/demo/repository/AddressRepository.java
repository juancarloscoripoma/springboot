package com.soft.demo.repository;

import com.soft.demo.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
}
