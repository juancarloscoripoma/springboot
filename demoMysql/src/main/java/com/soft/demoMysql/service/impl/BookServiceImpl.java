package com.soft.demoMysql.service.impl;

import com.soft.demoMysql.model.Book;
import com.soft.demoMysql.repository.BookRepository;
import com.soft.demoMysql.service.BookService;
import com.soft.demoMysql.service.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private Sort.Direction sort = Sort.Direction.ASC;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(BookDTO bookDTO) {
        Book book = new Book();
        book.setAuthor(bookDTO.getAuthor());

        book = bookRepository.save(book);
        return book;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Book> findAll(String orderBy, String direction, int page, int size/*Pageable pageable*/) {
        if(direction.equalsIgnoreCase("DESC")){
            sort = Sort.Direction.DESC;
        }

        Pageable pageable = PageRequest.of(page, size, sort, orderBy);
        return bookRepository.findAll(pageable);
    }
}
