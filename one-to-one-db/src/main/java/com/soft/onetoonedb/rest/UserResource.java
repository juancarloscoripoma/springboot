package com.soft.onetoonedb.rest;

import com.soft.onetoonedb.entity.Users;
import com.soft.onetoonedb.rest.util.HeaderUtil;
import com.soft.onetoonedb.service.UserService;
import com.soft.onetoonedb.service.dto.UsersDTO;
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
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);
    private static final String ENTITY_NAME = "users";

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<Users> createCity(@Valid @RequestBody UsersDTO usersDTO) throws URISyntaxException {
        log.debug("REST request to save Users : {}", usersDTO);
        if (usersDTO.getId() != null && usersDTO.getId() != 0 ) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("usersDTO", "idexists", "A new usersDTO cannot already have an ID")).body(null);
        }
        Users result = userService.save(usersDTO);
        return ResponseEntity.created(new URI("/api/users/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("usersDTO", result.getId().toString()))
                .body(result);
    }
}
