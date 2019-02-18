package com.soft.onetoonedb.service.impl;

import com.soft.onetoonedb.entity.Address;
import com.soft.onetoonedb.repository.AddressRepository;
import com.soft.onetoonedb.service.AddressService;
import com.soft.onetoonedb.service.dto.AddressDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address save(AddressDTO addressDTO) {
        Address address = new Address();
        address.setAddressLine1(addressDTO.getAddressLine1());
        address = addressRepository.save(address);
        return address;
    }
}
