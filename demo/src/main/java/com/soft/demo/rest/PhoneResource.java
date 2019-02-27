package com.soft.demo.rest;

import com.soft.demo.rest.util.HeaderUtil;
import com.soft.demo.rest.util.PaginationUtil;
import com.soft.demo.service.PhoneService;
import com.soft.demo.service.dto.PhoneDTO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class PhoneResource {

    private final Logger log = LoggerFactory.getLogger(PhoneResource.class);

    private static final String ENTITY_NAME = "phone";

    private final PhoneService phoneService;

    public PhoneResource(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @PostMapping("/phones")
    public ResponseEntity<PhoneDTO> createPhone(@Valid @RequestBody PhoneDTO phoneDTO) throws URISyntaxException {
        log.debug("REST request to save Phone : {}", phoneDTO);
        if (phoneDTO.getId() != null && phoneDTO.getId() != 0) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new phone cannot already have an ID")).body(null);
        }
        PhoneDTO result = phoneService.save(phoneDTO);
        return ResponseEntity.created(new URI("/api/phones/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @GetMapping("/phones")
    public ResponseEntity<List<PhoneDTO>> getAllPhones(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Phones");
        Page<PhoneDTO> page = phoneService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/phones");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderBy", dataType = "string", paramType = "query", value = "Order by the column.", defaultValue = "id"),
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page.", defaultValue = "50"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). " + "Default sort order is ascending. " + "Multiple sort criteria are supported.")
    })
    @ApiOperation(value = "Getting all phone")
    @GetMapping(value = "/phones/{id}", params = {"orderBy", "sort", "page", "size"})
    public ResponseEntity<List<PhoneDTO>> getAllPhoneById(@RequestParam("orderBy") String orderBy,
                                                          @RequestParam("sort") String sort,
                                                          @RequestParam("page") int page,
                                                          @RequestParam("size") int size,
                                                          @PathVariable Long id) {
        Page<PhoneDTO> result = phoneService.findPhoneById(orderBy, sort, page, size, id);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(result, "/api/phones/{id}");
        return new ResponseEntity<>(result.getContent(), headers, HttpStatus.OK);
    }
}
