package sdb.server.test.category;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import sdb.server.category.Category;
import sdb.server.category.CategoryRepository;
import sdb.server.category.contracts.CategoryCreateService;
import sdb.server.category.contracts.CategoryPersistenceService;
import sdb.server.category.dto.CategoryCreateRequest;
import sdb.server.category.dto.CategoryResponse;

@SpringBootTest
@AutoConfigureMockMvc 
public class CreateCategoryTest {
    @Autowired private MockMvc mockMvc;

    @Autowired private CategoryRepository categoryRepository;
    // private  CategoryAppService categoryAppService;
    @Autowired private CategoryCreateService categoryCreateService;
    @Autowired private CategoryPersistenceService categoryPersistenceService; 

    /**
     * Category controller create take  object of CategoryCreateRequest
     * Category controller create should return object of CategoryResponse
     */
    @Test
    public void categoryCreateControllerTest() throws Exception{
        //arrange
        CategoryCreateRequest request = new CategoryCreateRequest();
        request.setName("Cereal");
        request.setDescription("Cereal food items");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(request);

        //act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/category")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(jsonRequest)).andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();

        CategoryResponse response = objectMapper.readValue(jsonResponse, CategoryResponse.class);

        // assert
        assertEquals(201, mvcResult.getResponse().getStatus());
        assertEquals(request.getName(), response.getName());
        assertEquals(request.getDescription(), response.getDescription());

        //clean
        categoryRepository.deleteById(response.getId());
    }

    /**
     * Should take categoryCreateRequest
     * Map the request
     * App service should return CategoryResponse
     */
    @Test
    public void categoryCreateAppServiceTest(){
        //arrange
        CategoryCreateRequest request = new CategoryCreateRequest();
        request.setName("Cereal");
        request.setDescription("Cereal food items");

        //act
        

        //assert
    }

    @Test
    public void categoryCreateDomainService(){
        Category category = new Category();
        category.setName("Cereal");
        category.setDescription("Cereal food items");

        Category saved = categoryCreateService.create(category);

        assertEquals(category.getName(), saved.getName());
        assertEquals(category.getDescription(), saved.getDescription());

        categoryRepository.deleteById(saved.getId());
    }


    @Test
    public void categoryCreatePersistenceService(){
        Category category = new Category();
        category.setName("Cereal");
        category.setDescription("Cereal food items");

        Category saved = categoryPersistenceService.save(category);

        assertEquals(category.getName(), saved.getName());
        assertEquals(category.getDescription(), saved.getDescription());

        categoryRepository.deleteById(saved.getId());
    }
}
