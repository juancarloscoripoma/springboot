package com.soft.demoMysql.web.rest;

import com.soft.demoMysql.model.Book;
import com.soft.demoMysql.service.BookService;
import com.soft.demoMysql.service.dto.BookDTO;
import com.soft.demoMysql.web.rest.util.HeaderUtil;
import com.soft.demoMysql.web.rest.util.PaginationUtil;
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
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookResource {

    private final Logger log = LoggerFactory.getLogger(BookResource.class);
    private static final String ENTITY_NAME = "book";

    private final BookService bookService;
    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }


    @ApiOperation(value = "Save a book")
    @PostMapping("/books")
    public ResponseEntity<Book> createUser(@Valid @RequestBody BookDTO bookDTO) throws URISyntaxException {
        log.debug("REST request to save Book : {}", bookDTO);
        if (bookDTO.getId() != 0) { //null
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new book cannot already have an ID")).body(null);
        }
        Book result = bookService.save(bookDTO);
        return ResponseEntity.created(new URI("/api/books/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
}

    //@GetMapping("/books")
    /*
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page.", defaultValue = "5"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). " + "Default sort order is ascending. " + "Multiple sort criteria are supported.")
    })

    @ApiIgnore(
            "Ignored because swagger ui shows the wrong params, " + "instead they are explained in the implicit params"
    ) Pageable pageable
    */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderBy", dataType = "string", paramType = "query", value = "Order by the column.",defaultValue = "id"),
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)",defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page.",defaultValue = "50"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). "+"Default sort order is ascending. "+"Multiple sort criteria are supported.")
    })
    @ApiOperation(value = "Getting all books")
    @RequestMapping(value = "/books", params = { "orderBy", "sort", "page", "size" }, method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam("orderBy") String orderBy,
                                                  @RequestParam("sort") String sort,
                                                  @RequestParam("page") int page,
                                                  @RequestParam("size") int size) throws URISyntaxException {
        log.debug("REST request to get a page of Books");
        Page<Book> books = bookService.findAll(orderBy, sort, page, size);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(books, "/api/books");
        return new ResponseEntity<>(books.getContent(), headers, HttpStatus.OK);
    }

/*
    @ExceptionHandler(PaginationSortingException.class)
    public ResponseEntity<PagingSortingErrorResponse> exceptionHandler(Exception ex) {
        PagingSortingErrorResponse pagingSortingErrorResponse = new PagingSortingErrorResponse();
        pagingSortingErrorResponse.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        pagingSortingErrorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<PagingSortingErrorResponse>(pagingSortingErrorResponse, HttpStatus.OK);
    }
*/

}
