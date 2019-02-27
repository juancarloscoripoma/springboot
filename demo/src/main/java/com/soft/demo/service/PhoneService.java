package com.soft.demo.service;

import com.soft.demo.service.dto.PhoneDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PhoneService {
    PhoneDTO save(PhoneDTO phoneDTO);
    Page<PhoneDTO> findAll(Pageable pageable);
}
