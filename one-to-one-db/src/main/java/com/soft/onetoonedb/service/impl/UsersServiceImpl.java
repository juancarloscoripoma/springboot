package com.soft.onetoonedb.service.impl;

import com.soft.onetoonedb.entity.Address;
import com.soft.onetoonedb.entity.Users;
import com.soft.onetoonedb.repository.AddressRepository;
import com.soft.onetoonedb.repository.UserRepository;
import com.soft.onetoonedb.service.UserService;
import com.soft.onetoonedb.service.dto.UsersDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository usersRepository;
    //private final AddressRepository addressRepository;

    public UsersServiceImpl(UserRepository usersRepository/*, AddressRepository addressRepository*/) {
        this.usersRepository = usersRepository;
        //this.addressRepository = addressRepository;
    }

    @Override
    public Users save(UsersDTO usersDTO) {
        log.debug("Request to save Users : {}",usersDTO);
        Address address = new Address();
        address.setAddressLine1(usersDTO.getAddressLine1());

        Users users = new Users();

        users.setFirstName(usersDTO.getFirstName());
        //users.setAddress(address);

        //address.setUsers(users);



        //addressRepository.save(address);
        usersRepository.save(users);

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
