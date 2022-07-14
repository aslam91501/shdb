package sdb.server.category.contracts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import sdb.server.category.Category;

public interface CategoryRetrieveService {

    Category getByName(String name);

    Page<Category> getAll(Pageable pageable);

    
}
