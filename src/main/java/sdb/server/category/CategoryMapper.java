package sdb.server.category;

import org.mapstruct.Mapper;

import sdb.server.category.dto.CategoryCreateRequest;
import sdb.server.category.dto.CategoryResponse;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category categoryCreateRequest_Category(CategoryCreateRequest request);
    CategoryResponse category_CategoryResponse(Category category);
}
