package sdb.server.category.contracts;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import sdb.server.category.Category;

public interface CategoryPersistenceService {
    Category save(Category category);
    Category getByName(String name);
    Page<Category> getAll(Pageable pageable);
    Category getById(Long id);
    void delete(Long id);
    List<Category> saveAll(List<Category> categories);
}
