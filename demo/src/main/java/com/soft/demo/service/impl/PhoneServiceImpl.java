package com.soft.demo.service.impl;

import com.soft.demo.repository.PhoneRepository;
import com.soft.demo.service.PhoneService;
import com.soft.demo.service.dto.PhoneDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PhoneServiceImpl implements PhoneService {

    private final Logger log = LoggerFactory.getLogger(PhoneServiceImpl.class);

    private final PhoneRepository phoneRepository;

    public PhoneServiceImpl(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Override
    public PhoneDTO save(PhoneDTO phoneDTO) {
        log.debug("Request to save Phone : {}", phoneDTO);
        return null;
    }

    @Override
    public Page<PhoneDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Phones");
        return null;
    }
}
