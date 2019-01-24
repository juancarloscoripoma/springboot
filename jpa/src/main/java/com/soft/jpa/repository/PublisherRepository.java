package com.soft.jpa.repository;

import com.soft.jpa.model.Publisher;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jcarlos on 1/24/2019.
 */
public interface PublisherRepository extends CrudRepository<Publisher,Long> {
}
