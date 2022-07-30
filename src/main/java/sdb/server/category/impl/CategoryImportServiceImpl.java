package sdb.server.category.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import sdb.server.category.Category;
import sdb.server.category.contracts.CategoryCreateService;
import sdb.server.category.contracts.CategoryImportService;
import sdb.server.category.exceptions.CategoryExcelException;

@Service @RequiredArgsConstructor
public class CategoryImportServiceImpl implements CategoryImportService {
    private final CategoryCreateService categoryCreateService;

    @Override
    public List<Category> importExcel(MultipartFile file) {
        List<Category> categories = new ArrayList<>();

        try{
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet workSheet = workbook.getSheetAt(0);

            for(int index = 0; index < workSheet.getPhysicalNumberOfRows(); index++){
                if(index > 0){
                    Row row = workSheet.getRow(index);
                    Category category = new Category();

                    category.setName(row.getCell(0).getStringCellValue());
                    category.setDescription(row.getCell(1).getStringCellValue());

                    categories.add(categoryCreateService.create(category));
                }
            }

            return categories;
        }
        catch(IOException exception){
            System.out.println(exception.getMessage());
            throw new CategoryExcelException("Something went wrong importing the excel file.");
        }
    }
    
}
