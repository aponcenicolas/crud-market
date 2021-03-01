package lux.pe.na.market.implementation;

import lux.pe.na.market.validation.ValidNotFoundException;
import lux.pe.na.market.model.dto.CategoryDto;
import lux.pe.na.market.model.entity.Category;
import lux.pe.na.market.repository.CategoryRepository;
import lux.pe.na.market.service.CategoryService;

import static lux.pe.na.market.utils.DataStatus.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    private CategoryDto convertToDto(Category category) {
        return mapper.map(category, CategoryDto.class);
    }

    private Category convertToEntity(CategoryDto categoryDto) {
        return mapper.map(categoryDto, Category.class);
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categories = repository.findByStatus(ENABLED);
        return categories.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto grtById(Long id) {
        return convertToDto(repository.findByStatusAndId(ENABLED, id)
                .orElseThrow(() -> new ValidNotFoundException(CATEGORY_MESSAGE + id)));
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        categoryDto.setStatus(ENABLED);
        Category category = convertToEntity(categoryDto);
        return convertToDto(repository.save(category));
    }

    @Override
    public CategoryDto update(Long id, CategoryDto categoryDto) {
        Category category = repository.findByStatusAndId(ENABLED, id)
                .orElseThrow(() -> new ValidNotFoundException(CATEGORY_MESSAGE + id));
        category.setName(categoryDto.getName());
        return convertToDto(repository.save(category));
    }

    @Override
    public CategoryDto delete(Long id) {
        Category category = repository.findByStatusAndId(ENABLED, id)
                .orElseThrow(() -> new ValidNotFoundException(CATEGORY_MESSAGE + id));
        category.setStatus(DISABLED);
        return convertToDto(repository.save(category));
    }
}
