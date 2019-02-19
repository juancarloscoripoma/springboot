package com.soft.onetoonedb.service.impl;

import com.soft.onetoonedb.entity.Address;
import com.soft.onetoonedb.entity.Users;
import com.soft.onetoonedb.repository.AddressRepository;
import com.soft.onetoonedb.repository.UserRepository;
import com.soft.onetoonedb.service.AddressService;
import com.soft.onetoonedb.service.UserService;
import com.soft.onetoonedb.service.dto.UsersDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsersServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository usersRepository;
    private final AddressService addressService;

    public UsersServiceImpl(UserRepository usersRepository, AddressService addressService) {
        this.usersRepository = usersRepository;
        this.addressService = addressService;
    }

    @Override
    public Users save(UsersDTO usersDTO) {
        log.debug("Request to save Users : {}",usersDTO);
        Address address = new Address(usersDTO.getAddressline1());


        Users users = new Users(usersDTO.getFirstname(), address);

        //address.setUsers(users);

        addressService.save(address);
        users = usersRepository.save(users);

        return users;
    }

    @Override
    public Page<Users> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Users findOne(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
