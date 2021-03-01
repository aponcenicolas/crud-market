package lux.pe.na.market.service;

import lux.pe.na.market.model.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();

    CategoryDto grtById(Long id);

    CategoryDto save(CategoryDto categoryDto);

    CategoryDto update(Long id, CategoryDto categoryDto);

    CategoryDto delete(Long id);
}
