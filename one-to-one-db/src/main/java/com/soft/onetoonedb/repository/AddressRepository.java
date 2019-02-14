package com.soft.onetoonedb.repository;

import com.soft.onetoonedb.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
