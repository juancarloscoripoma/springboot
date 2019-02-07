package com.soft.microservices.service.impl;

import com.soft.microservices.model.Client;
import com.soft.microservices.repository.ClientRepository;
import com.soft.microservices.service.ClientService;
import com.soft.microservices.service.dto.ClientDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client save(ClientDTO clientDTO) {
        Client client = new Client();
        client.setNit(clientDTO.getNit());
        client.setReasonsocial(clientDTO.getReasonsocial());

        clientRepository.save(client);
        return client;
    }

    @Override
    public Page<Client> findAll(String orderBy, String direction, int page, int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, orderBy);
        return clientRepository.findAll(pageable);
    }

    @Override
    public Client findOne(Long id) {
        return clientRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        clientRepository.delete(id);
    }
}
