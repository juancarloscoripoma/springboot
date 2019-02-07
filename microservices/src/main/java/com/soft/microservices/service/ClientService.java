package com.soft.microservices.service;

import com.soft.microservices.model.Client;
import com.soft.microservices.service.dto.ClientDTO;
import org.springframework.data.domain.Page;

public interface ClientService {

    Client save(ClientDTO clientDTO);
    Page<Client> findAll(String orderBy, String direction, int page, int size);
    Client findOne(Long id);
    void delete(Long id);
}
