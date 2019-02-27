package com.soft.demo.rest;

import com.soft.demo.entity.Users;
import com.soft.demo.rest.util.HeaderUtil;
import com.soft.demo.rest.util.PaginationUtil;
import com.soft.demo.service.UserService;
import com.soft.demo.service.dto.UsersDTO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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
    public ResponseEntity<UsersDTO> createUser(@Valid @RequestBody UsersDTO usersDTO) throws URISyntaxException {
        log.debug("REST request to save UsersDTO : {}", usersDTO);
        if (usersDTO.getId() != null && usersDTO.getId() != 0) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new users cannot already have an ID")).body(null);
        }
        UsersDTO result = userService.save(usersDTO);
        return ResponseEntity.created(new URI("/api/users/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderBy", dataType = "string", paramType = "query", value = "Order by the column.",defaultValue = "id"),
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)",defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page.",defaultValue = "50"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). "+"Default sort order is ascending. "+"Multiple sort criteria are supported.")
    })
    @ApiOperation(value = "Getting all users")
    @GetMapping(value = "/users", params = {"orderBy", "sort", "page", "size"})
    public ResponseEntity<List<UsersDTO>> getAllUsers(@RequestParam("orderBy") String orderBy,
                                                            @RequestParam("sort") String sort,
                                                            @RequestParam("page") int page,
                                                            @RequestParam("size") int size) {
        log.debug("REST request to get a page of Users");
        Page<UsersDTO> employeeDTOS = userService.findAll(orderBy, sort, page, size);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(employeeDTOS, "/api/users");
        return new ResponseEntity<>(employeeDTOS.getContent(), headers, HttpStatus.OK);
    }
}
