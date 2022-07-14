package sdb.server.test.category;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import sdb.server.category.Category;
import sdb.server.category.CategoryRepository;
import sdb.server.category.dto.CategoryPagedResponse;
import sdb.server.category.dto.CategoryResponse;

@SpringBootTest
@AutoConfigureMockMvc
public class RetrieveCategoryTest {
    @Autowired MockMvc mockMvc;
    @Autowired CategoryRepository categoryRepository;

    @Test 
    public void getCategoryControllerTest() throws Exception{
        // arrange
        Category category = new Category();
        category.setName("Cereals");
        category.setDescription("Cereal Description");

        Category saved = categoryRepository.save(category);

        //act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/category/Cereals")
        ).andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        CategoryResponse response = objectMapper.readValue(jsonResponse, CategoryResponse.class);


        //assert
        assertEquals(category.getName(), response.getName());
        assertEquals(category.getDescription(), response.getDescription());

        //clean
        categoryRepository.deleteById(saved.getId());
    }

    @Test 
    public void getCategoryPagedControllerTest() throws Exception{
        categoryRepository.deleteAll();

        // arrange
        Category category = new Category();
        category.setName("Cereals");
        category.setDescription("Cereal Description");

        Category category2 = new Category();
        category.setName("Cereals2");
        category.setDescription("Cereal Description");

        Category saved = categoryRepository.save(category);
        Category saved2 = categoryRepository.save(category2);

        //act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/category?page=1&size=10")
        ).andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        CategoryPagedResponse response = objectMapper.readValue(jsonResponse, CategoryPagedResponse.class);


        //assert
        assertEquals(2, response.getItems().size());

        //clean
        categoryRepository.deleteAll();
    }


    
}
