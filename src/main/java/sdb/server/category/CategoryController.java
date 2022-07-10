package sdb.server.category;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sdb.server.category.contracts.CategoryAppService;
import sdb.server.category.dto.CategoryCreateRequest;
import sdb.server.category.dto.CategoryResponse;

@RestController @RequestMapping("api/category") @RequiredArgsConstructor
public class CategoryController {
    private final CategoryAppService categoryAppService;

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody @Valid CategoryCreateRequest request){
        CategoryResponse response = categoryAppService.create(request);

        return new ResponseEntity<CategoryResponse>(response, HttpStatus.CREATED);
    }
}
