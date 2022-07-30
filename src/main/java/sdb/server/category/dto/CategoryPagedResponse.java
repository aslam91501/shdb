package sdb.server.category.dto;

import java.util.List;

import lombok.Data;

@Data
public class CategoryPagedResponse {
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private Long totalElements;
    private List<CategoryResponse> items;
}
