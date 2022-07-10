package sdb.server.category.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sdb.server.category.Category;
import sdb.server.category.CategoryRepository;
import sdb.server.category.contracts.CategoryPersistenceService;

@Service @RequiredArgsConstructor
public class CategoryPersistenceServiceImpl implements CategoryPersistenceService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getByName(String name) {
        return categoryRepository.findByName(name);
    }
}
