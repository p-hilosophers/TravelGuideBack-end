package com.travelg.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelg.Model.City;
import com.travelg.Model.User;
import com.travelg.Repository.CityRepository;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CityControllerMockMvcStandaloneTest {

    private MockMvc mvc;

    @Mock
    CityRepository cityRepository;

    @InjectMocks
    private CityController cityController;

    private JacksonTester<City> jsonCity;

    @Before
    public void setup()
    {
        JacksonTester.initFields(this,new ObjectMapper());

        mvc = MockMvcBuilders.standaloneSetup(cityController)
                .build();
    }

    @Test
    public void canFindByName() throws Exception
    {
        UUID uuid = UUID.randomUUID();
        given(cityRepository.findByName("Athens"))
                .willReturn(new City("Athens",0.0,0.0,""));

        MockHttpServletResponse response = mvc.perform(
                get("/cities/Athens")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

            assertEquals(response.getStatus(), HttpStatus.OK.value());
    }

    @Test
    public void canSaveCity() throws Exception
    {
        MockHttpServletResponse response = mvc.perform(
                post("/cities").contentType(MediaType.APPLICATION_JSON).content(
                        jsonCity.write(new City("Athens",0.0,0.0,"12345")).getJson()
                )).andReturn().getResponse();

        assertEquals(response.getStatus(),HttpStatus.OK.value());

    }

    @Test
    public void shouldHaveEmptyDB() throws Exception
    {

        mvc.perform(get("/cities")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(0)));
    }

}
