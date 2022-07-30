package sdb.server.category;

import java.util.List;

import javax.validation.Valid;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import sdb.server.category.contracts.CategoryAppService;
import sdb.server.category.dto.CategoryCreateRequest;
import sdb.server.category.dto.CategoryPagedResponse;
import sdb.server.category.dto.CategoryResponse;
import org.springframework.web.bind.annotation.RequestParam;


@RestController @RequestMapping("api/categories") @RequiredArgsConstructor
public class CategoryController {
    private final CategoryAppService categoryAppService;

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody @Valid CategoryCreateRequest request){
        CategoryResponse response = categoryAppService.create(request);

        return new ResponseEntity<CategoryResponse>(response, HttpStatus.CREATED);
    }

    @GetMapping("{name}")
    public ResponseEntity<CategoryResponse> get(@PathVariable("name") String name){
        CategoryResponse response = categoryAppService.getByName(name);

        return new ResponseEntity<CategoryResponse>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CategoryPagedResponse> getPaged(@RequestParam("page") int page, @RequestParam("size") int size) {
        CategoryPagedResponse response = categoryAppService.getAll(page, size);

        return new ResponseEntity<CategoryPagedResponse>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        categoryAppService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<CategoryResponse>> importExcel(@RequestParam("file") MultipartFile files){
        List<CategoryResponse> createdEntities = categoryAppService.importExcel(files);
        
        return new ResponseEntity<>(createdEntities, HttpStatus.CREATED);
    }

}
