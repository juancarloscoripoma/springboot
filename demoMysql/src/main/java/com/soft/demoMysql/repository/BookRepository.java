package com.soft.demoMysql.repository;

import com.soft.demoMysql.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
