package lux.pe.na.market.controller;

import lux.pe.na.market.model.dto.ProductDto;
import lux.pe.na.market.model.dto.ProductListDto;
import lux.pe.na.market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/products")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductListDto>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<ProductDto> save(@Valid @RequestBody ProductDto productDto) {
        return new ResponseEntity<>(service.save(productDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @Valid @RequestBody ProductDto productDto) {
        return new ResponseEntity<>(service.update(id, productDto), HttpStatus.CREATED);

    }

    @PreAuthorize("hasRole('ADMIN')")
    public @DeleteMapping(value = "/{id}")
    ResponseEntity<ProductDto> delete(@PathVariable Long id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.NO_CONTENT);
    }
}
