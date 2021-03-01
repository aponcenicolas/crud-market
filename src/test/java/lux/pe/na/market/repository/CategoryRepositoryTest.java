package lux.pe.na.market.repository;

import lux.pe.na.market.model.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repository;

    @Test
    void findByStatus() {
        List<Category> categories = repository.findByStatus(true);
        assertEquals(4, categories.size());
    }

    @Test
    void findByStatusAndId() {
        Category category = repository.findByStatusAndId(true, 1L).orElse(null);
        assertEquals(1, category.getId());
    }
}