package sdb.server.category.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sdb.server.category.Category;
import sdb.server.category.contracts.CategoryCreateService;
import sdb.server.category.contracts.CategoryPersistenceService;
import sdb.server.category.exceptions.CategoryAlreadyExistsException;

@Service @RequiredArgsConstructor
public class CategoryCreateServiceImpl implements CategoryCreateService {
    private final CategoryPersistenceService persistenceService;

    @Override
    public Category create(Category category) {
        if(persistenceService.getByName(category.getName()) == null)
            return persistenceService.save(category);
        else throw new CategoryAlreadyExistsException();
    }
    
}
