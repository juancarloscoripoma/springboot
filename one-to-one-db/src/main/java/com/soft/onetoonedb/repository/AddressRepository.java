package com.soft.onetoonedb.repository;

import com.soft.onetoonedb.entity.Address;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
}
