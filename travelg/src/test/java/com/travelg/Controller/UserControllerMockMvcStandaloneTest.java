package com.travelg.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelg.Model.User;
import com.travelg.Repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerMockMvcStandaloneTest {

    private MockMvc mvc;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private  UserController userController;

    private  JacksonTester<User> jsonUser;

    @Before
    public void setup()
    {
        JacksonTester.initFields(this,new ObjectMapper());

        mvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
    }

    @Test
    public void canFindByNameAndPassword() throws Exception {

        UUID uuid = UUID.randomUUID();
        given(userRepository.findByEmailAndPassword("a@email.com","12345"))
                .willReturn(new User(uuid,"a","b","a@email.com","12345"));

        MockHttpServletResponse response = mvc.perform(
                get("/users/a@email.com/12345")
                    .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(response.getStatus(),HttpStatus.OK.value());
        assertEquals(response.getContentAsString(),jsonUser.write(new User(uuid,"a","b","a@email.com","12345")).getJson());
    }

    @Test
    public void canSaveUser() throws Exception
    {
        MockHttpServletResponse response = mvc.perform(
                post("/users").contentType(MediaType.APPLICATION_JSON).content(
                        jsonUser.write(new User(UUID.randomUUID(),"a","b","a@email.com","12345")).getJson()
                )).andReturn().getResponse();

        assertEquals(response.getStatus(),HttpStatus.OK.value());

    }

    @Test
    public void canDeleteUser() throws Exception
    {
        UUID uuid = UUID.randomUUID();
         mvc.perform(
                post("/users").contentType(MediaType.APPLICATION_JSON).content(
                        jsonUser.write(new User(uuid,"a","b","a@email.com","12345")).getJson()
                )).andReturn().getResponse();
        MockHttpServletResponse response = mvc.perform(
                delete( "/users/" + uuid))
                .andReturn().getResponse();


        assertEquals(response.getStatus(),HttpStatus.OK.value());
    }

    @Test
    public void shouldHaveEmptyDB() throws Exception
    {
        mvc.perform(get("/users")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(0)));
    }

}
