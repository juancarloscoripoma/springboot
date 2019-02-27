package com.soft.demo.service.impl;

import com.soft.demo.entity.Address;
import com.soft.demo.entity.Users;
import com.soft.demo.repository.AddressRepository;
import com.soft.demo.repository.UserRepository;
import com.soft.demo.service.UserService;
import com.soft.demo.service.dto.UsersDTO;
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
public class UsersServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository usersRepository;
    private final AddressRepository addressRepository;
    private Sort.Direction sort = Sort.Direction.ASC;

    public UsersServiceImpl(UserRepository usersRepository, AddressRepository addressRepository) {
        this.usersRepository = usersRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public UsersDTO save(UsersDTO usersDTO) {
        log.debug("Request to save UsersDTO : {}", usersDTO);
        Address address = new Address(usersDTO.getAddressDTO().getAddressline1());

        Users users = new Users(usersDTO.getFirstname(), address);

        address = addressRepository.save(address);
        users = usersRepository.save(users);

        usersDTO.setId(users.getId());
        usersDTO.getAddressDTO().setId(address.getId());
        return usersDTO;
    }

    @Override
    public Page<UsersDTO> findAll(String orderBy, String direction, int page, int size) {
        log.debug("Request to get all Users");
        if (direction.equalsIgnoreCase("DESC")) {
            sort = Sort.Direction.DESC;
        }
        Pageable pageable = new PageRequest(page, size, sort, orderBy);

        Page<Users> result = usersRepository.findAll(pageable);
        return result.map(users -> new UsersDTO(users.getId(),users.getFirstname()));
    }

    @Override
    public UsersDTO findOne(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
