package com.soft.jpa.repository;

import com.soft.jpa.model.Book;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Administrator on 1/23/2019.
 */
public interface BookRepository extends CrudRepository<Book, Long> {
}
