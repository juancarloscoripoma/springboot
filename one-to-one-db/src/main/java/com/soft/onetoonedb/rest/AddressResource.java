package com.soft.onetoonedb.rest;

import com.soft.onetoonedb.entity.Address;
import com.soft.onetoonedb.rest.util.HeaderUtil;
import com.soft.onetoonedb.service.AddressService;
import com.soft.onetoonedb.service.dto.AddressDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class AddressResource {

    private final Logger log = LoggerFactory.getLogger(AddressResource.class);
    private final AddressService addressService;

    public AddressResource(AddressService addressService) {
        this.addressService = addressService;
    }


    @PostMapping("/addresses")
    public ResponseEntity<Address> createAddress(@Valid @RequestBody AddressDTO addressDTO) throws URISyntaxException {
        log.debug("REST request to save Addresses : {}", addressDTO);
        if (addressDTO.getId() != null && addressDTO.getId() != 0 ) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("addressDTO", "idexists", "A new addressDTO cannot already have an ID")).body(null);
        }
        Address result = addressService.save(addressDTO);
        return ResponseEntity.created(new URI("/api/addresses/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("addressDTO", result.getId().toString()))
                .body(result);
    }

}
