package sdb.server.category.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import sdb.server.category.Category;
import sdb.server.category.contracts.CategoryPersistenceService;
import sdb.server.category.contracts.CategoryRetrieveService;

@Component @RequiredArgsConstructor
public class CategoryRetrieveServiceImpl implements CategoryRetrieveService {
    private final CategoryPersistenceService categoryPersistenceService;
    
    @Override
    public Category getByName(String name) {
        Category category = categoryPersistenceService.getByName(name);

        if(category != null)
            return category;
        else throw new RuntimeException("No category found with that name");
    }

    @Override
    public Page<Category> getAll(Pageable pageable) {
        return categoryPersistenceService.getAll(pageable);
    }
}
