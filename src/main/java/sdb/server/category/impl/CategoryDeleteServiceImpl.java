package sdb.server.category.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sdb.server.category.contracts.CategoryDeleteService;
import sdb.server.category.contracts.CategoryPersistenceService;

@Service @RequiredArgsConstructor
public class CategoryDeleteServiceImpl implements CategoryDeleteService{
    private final CategoryPersistenceService persistenceService;

    @Override
    public void delete(Long id) {
        if(persistenceService.getById(id) != null)
            persistenceService.delete(id);
    }
}
