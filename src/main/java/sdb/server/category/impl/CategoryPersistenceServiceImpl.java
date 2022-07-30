package sdb.server.category.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<Category> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category getById(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        
        if(categoryOptional.isPresent())
            return categoryOptional.get();
        else return null;
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> saveAll(List<Category> categories) {
        return categoryRepository.saveAll(categories);
    }
}
