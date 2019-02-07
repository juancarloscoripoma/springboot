package com.soft.demoMysql.service;

import com.soft.demoMysql.model.Book;
import com.soft.demoMysql.service.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    Book save(BookDTO bookDTO);
    Page<Book> findAll(String orderBy, String direction, int page, int size/*Pageable pageable*/);
}
