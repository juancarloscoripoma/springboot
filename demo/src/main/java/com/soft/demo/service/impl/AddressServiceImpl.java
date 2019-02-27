package com.soft.demo.service.impl;

import com.soft.demo.entity.Address;
import com.soft.demo.repository.AddressRepository;
import com.soft.demo.service.AddressService;
import com.soft.demo.service.dto.AddressDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public AddressDTO save(AddressDTO addressDTO) {
        Address address = new Address();
        address.setAddressline1(addressDTO.getAddressline1());
        address = addressRepository.save(address);

        address.setId(address.getId());
        return addressDTO;
    }
}
