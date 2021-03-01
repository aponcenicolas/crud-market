package lux.pe.na.market.repository;

import lux.pe.na.market.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByStatus(int status);

    Optional<Category> findByStatusAndId(int status, Long aLong);
}
