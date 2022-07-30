package sdb.server.category.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import sdb.server.category.Category;
import sdb.server.category.CategoryMapper;
import sdb.server.category.contracts.CategoryAppService;
import sdb.server.category.contracts.CategoryCreateService;
import sdb.server.category.contracts.CategoryDeleteService;
import sdb.server.category.contracts.CategoryImportService;
import sdb.server.category.contracts.CategoryRetrieveService;
import sdb.server.category.dto.CategoryCreateRequest;
import sdb.server.category.dto.CategoryPagedResponse;
import sdb.server.category.dto.CategoryResponse;

@Service @RequiredArgsConstructor
public class CategoryAppServiceImpl implements CategoryAppService {
    private final CategoryMapper categoryMapper;
    private final CategoryCreateService categoryCreateService;
    private final CategoryRetrieveService categoryRetrieveService;
    private final CategoryDeleteService categoryDeleteService;
    private final CategoryImportService categoryImportService;

    @Override
    public CategoryResponse create(@Valid CategoryCreateRequest request) {
        Category created = categoryCreateService.create(categoryMapper.categoryCreateRequest_Category(request));        

        return categoryMapper.category_CategoryResponse(created);
    }

    @Override
    public CategoryResponse getByName(String name) {
        Category category = categoryRetrieveService.getByName(name);
        return categoryMapper.category_CategoryResponse(category);
    }

    @Override
    public CategoryPagedResponse getAll(int page, int size) {
        Page<Category> categoryPage = categoryRetrieveService.getAll(PageRequest.of(page - 1, size));

        List<CategoryResponse> responses = categoryPage.getContent().stream().map(categoryMapper::category_CategoryResponse).collect(Collectors.toList());

        CategoryPagedResponse pagedResponse = new CategoryPagedResponse();
        pagedResponse.setItems(responses);
        pagedResponse.setPageNumber(categoryPage.getNumber());
        pagedResponse.setPageSize(categoryPage.getSize());
        pagedResponse.setTotalPages(categoryPage.getTotalPages());
        pagedResponse.setTotalElements(categoryPage.getTotalElements());

        return pagedResponse;
    }

    @Override
    public void delete(Long id) {
        categoryDeleteService.delete(id);
    }

    @Override
    public List<CategoryResponse> importExcel(MultipartFile files){
        List<Category> categories = categoryImportService.importExcel(files);

        return categories.stream()
            .map(categoryMapper::category_CategoryResponse)
            .collect(Collectors.toList());

    }
    
}
