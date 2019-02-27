package com.soft.demo.service;

import com.soft.demo.entity.Phone;
import com.soft.demo.service.dto.PhoneDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PhoneService {
    PhoneDTO save(PhoneDTO phoneDTO);
    Page<PhoneDTO> findAll(Pageable pageable);
    Page<PhoneDTO> findPhoneById(String orderBy, String direction, int page, int size, Long id);
}
