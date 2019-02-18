package com.soft.onetoonedb.service;

import com.soft.onetoonedb.entity.Address;
import com.soft.onetoonedb.service.dto.AddressDTO;

public interface AddressService {
    Address save(AddressDTO addressDTO);
}
