package sdb.server.category.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CategoryCreateRequest {
    @NotNull private String name;
    @NotNull private String description;
}
