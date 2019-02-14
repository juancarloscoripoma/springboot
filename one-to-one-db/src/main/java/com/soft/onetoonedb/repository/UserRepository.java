package com.soft.onetoonedb.repository;

import com.soft.onetoonedb.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
