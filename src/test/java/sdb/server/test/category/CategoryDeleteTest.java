package sdb.server.test.category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import sdb.server.category.Category;
import sdb.server.category.CategoryRepository;
import sdb.server.category.contracts.CategoryPersistenceService;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryDeleteTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private CategoryPersistenceService persistenceService;

    @Test
    public void categoryDeleteControllerTest() throws Exception{
        categoryRepository.deleteAll();
        
        Category category = new Category();
        category.setName("Notebooks");
        category.setDescription("Notebooks xyz...");

        Category saved = categoryRepository.save(category);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/category/{id}", saved.getId()))
            .andExpect(status().isOk());


        assertEquals(null, persistenceService.getById(saved.getId()));

        categoryRepository.deleteAll();
    }
}
