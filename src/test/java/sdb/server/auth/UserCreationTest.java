package sdb.server.auth;

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

import sdb.server.auth.dto.UserCreateRequest;
import sdb.server.auth.dto.UserResponse;
import sdb.server.auth.repo.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class UserCreationTest {
    @Autowired private MockMvc mockMvc;
    // @Autowired private UserRepository userRepository;

    @Test
    public void WhenUserCreateRequestSend_ShouldReturnResponse() throws Exception{
        UserCreateRequest request = new UserCreateRequest();
        request.setUsername("Username");
        request.setPassword("aslam@123");

        ObjectMapper mapper = new ObjectMapper();
        String jsonRequest = mapper.writeValueAsString(request);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(jsonRequest)).andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        UserResponse response = mapper.readValue(jsonResponse, UserResponse.class);

        assertEquals(mvcResult.getResponse().getStatus(), 201);
        assertEquals(request.getUsername(), response.getUsername());
    }
}
