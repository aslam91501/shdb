package sdb.server.category.contracts;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import sdb.server.category.Category;

public interface CategoryImportService {
    List<Category> importExcel(MultipartFile file);
}
