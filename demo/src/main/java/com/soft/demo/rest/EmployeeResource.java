package com.soft.demo.rest;

import com.soft.demo.rest.util.HeaderUtil;
import com.soft.demo.rest.util.PaginationUtil;
import com.soft.demo.service.EmployeeService;
import com.soft.demo.service.dto.EmployeeDTO;
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
public class EmployeeResource {

    private final Logger log = LoggerFactory.getLogger(EmployeeResource.class);

    private static final String ENTITY_NAME = "employee";

    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) throws URISyntaxException {
        log.debug("REST request to save Employee : {}", employeeDTO);
        if (employeeDTO.getId() != null && employeeDTO.getId() != 0) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new employee cannot already have an ID")).body(null);
        }
        EmployeeDTO result = employeeService.save(employeeDTO);
        return ResponseEntity.created(new URI("/api/employees/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderBy", dataType = "string", paramType = "query", value = "Order by the column.",defaultValue = "id"),
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)",defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page.",defaultValue = "50"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). "+"Default sort order is ascending. "+"Multiple sort criteria are supported.")
    })
    @ApiOperation(value = "Getting all books")
    @GetMapping(value = "/employees", params = {"orderBy", "sort", "page", "size"})
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(@RequestParam("orderBy") String orderBy,
                                                            @RequestParam("sort") String sort,
                                                            @RequestParam("page") int page,
                                                            @RequestParam("size") int size) {
        log.debug("REST request to get a page of Employee");
        Page<EmployeeDTO> employeeDTOS = employeeService.findAll(orderBy, sort, page, size);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(employeeDTOS, "/api/employees");
        return new ResponseEntity<>(employeeDTOS.getContent(), headers, HttpStatus.OK);
    }

}
