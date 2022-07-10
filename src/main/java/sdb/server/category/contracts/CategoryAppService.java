package sdb.server.category.contracts;

import javax.validation.Valid;

import sdb.server.category.dto.CategoryCreateRequest;
import sdb.server.category.dto.CategoryResponse;

public interface CategoryAppService {

    CategoryResponse create(@Valid CategoryCreateRequest request);
    
}
