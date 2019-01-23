package com.soft.jpa.repository;

import com.soft.jpa.model.Author;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jcarlos on 1/22/2019.
 */
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
