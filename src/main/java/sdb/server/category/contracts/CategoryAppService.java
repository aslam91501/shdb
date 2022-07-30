package sdb.server.category.contracts;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import sdb.server.category.dto.CategoryCreateRequest;
import sdb.server.category.dto.CategoryPagedResponse;
import sdb.server.category.dto.CategoryResponse;

public interface CategoryAppService {

    CategoryResponse create(@Valid CategoryCreateRequest request);

    CategoryResponse getByName(String name);

    CategoryPagedResponse getAll(int page, int size);

    void delete(Long id);

    List<CategoryResponse> importExcel(MultipartFile files);
    
}
