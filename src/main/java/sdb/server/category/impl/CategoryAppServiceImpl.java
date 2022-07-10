package sdb.server.category.impl;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import sdb.server.category.Category;
import sdb.server.category.CategoryMapper;
import sdb.server.category.contracts.CategoryAppService;
import sdb.server.category.contracts.CategoryCreateService;
import sdb.server.category.dto.CategoryCreateRequest;
import sdb.server.category.dto.CategoryResponse;

@Service @RequiredArgsConstructor
public class CategoryAppServiceImpl implements CategoryAppService {
    private final CategoryMapper categoryMapper;
    private final CategoryCreateService categoryCreateService;

    @Override
    public CategoryResponse create(@Valid CategoryCreateRequest request) {
        Category created = categoryCreateService.create(categoryMapper.categoryCreateRequest_Category(request));        

        return categoryMapper.category_CategoryResponse(created);
    }
    
}
