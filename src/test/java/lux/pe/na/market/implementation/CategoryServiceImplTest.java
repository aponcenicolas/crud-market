package lux.pe.na.market.implementation;

import lux.pe.na.market.model.dto.CategoryDto;
import lux.pe.na.market.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceImplTest {


    @Mock
    private CategoryServiceImpl service;

    @BeforeEach
    void setUp() {
        List<CategoryDto> list = new ArrayList<>();
        list.add(CategoryDto.builder().id(1L).name("Bebidas").status(true).build());
        list.add(CategoryDto.builder().id(2L).name("Lacteos").status(true).build());
        Mockito.when(service.findAll()).thenReturn(list);
    }

    @Test
    void findAll() {
        List<CategoryDto> list = service.findAll();
        assertEquals(2, list.size());
    }

    @Test
    void grtById() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}