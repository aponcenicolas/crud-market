package lux.pe.na.market.implementation;

import lux.pe.na.market.model.dto.ProductListDto;
import lux.pe.na.market.validation.ValidNotFoundException;
import lux.pe.na.market.model.dto.ProductDto;
import lux.pe.na.market.model.entity.Product;
import lux.pe.na.market.repository.ProductRepository;
import lux.pe.na.market.service.ProductService;

import static lux.pe.na.market.utils.DataStatus.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    private ProductDto convertToDto(Product product) {
        return mapper.map(product, ProductDto.class);
    }

    private ProductListDto convertToListDto(Product product) {
        return mapper.map(product, ProductListDto.class);
    }

    private Product convertToEntity(ProductDto productDto) {
        return mapper.map(productDto, Product.class);
    }

    @Override
    public List<ProductListDto> findAll() {
        List<Product> products = repository.findByStatus(ENABLED);
        return products.stream()
                .map(this::convertToListDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getById(Long id) {
        return convertToDto(repository.findByStatusAndId(ENABLED, id)
                .orElseThrow(() -> new ValidNotFoundException(PRODUCT_MESSAGE + id)));

    }

    @Override
    public ProductDto save(ProductDto productDto) {
        productDto.setStatus(ENABLED);
        Product product = convertToEntity(productDto);
        return convertToDto(repository.save(product));
    }

    @Override
    public ProductDto update(Long id, ProductDto productDto) {
        ProductDto edit = convertToDto(repository.findByStatusAndId(ENABLED, id)
                .orElseThrow(() -> new ValidNotFoundException(PRODUCT_MESSAGE + id)));
        edit.setName(productDto.getName());
        edit.setStock(productDto.getStock());
        edit.setPrice(productDto.getPrice());
        edit.setCategoryId(productDto.getCategoryId());
        Product product = convertToEntity(edit);
        return convertToDto(repository.save(product));
    }

    @Override
    public ProductDto delete(Long id) {
        ProductDto drop = convertToDto(repository.findByStatusAndId(ENABLED, id)
                .orElseThrow(() -> new ValidNotFoundException(PRODUCT_MESSAGE + id)));
        drop.setStatus(DISABLED);
        Product product = convertToEntity(drop);
        return convertToDto(repository.save(product));
    }
}
