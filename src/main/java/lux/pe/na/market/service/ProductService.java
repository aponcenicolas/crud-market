package lux.pe.na.market.service;

import lux.pe.na.market.model.dto.ProductDto;
import lux.pe.na.market.model.dto.ProductListDto;

import java.util.List;

public interface ProductService {
    List<ProductListDto> findAll();

    ProductDto getById(Long id);

    ProductDto save(ProductDto productDto);

    ProductDto update(Long id, ProductDto productDto);

    ProductDto delete(Long id);
}
