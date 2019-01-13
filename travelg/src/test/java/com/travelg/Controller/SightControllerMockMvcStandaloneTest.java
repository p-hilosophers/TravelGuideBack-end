package com.travelg.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelg.Model.City;
import com.travelg.Model.Sight;
import com.travelg.Model.User;
import com.travelg.Repository.SightRepositoryTest;
import com.travelg.Repository.SightsRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class SightControllerMockMvcStandaloneTest {

    private MockMvc mvc;

    @Mock
    SightsRepository sightSRepository;

    @InjectMocks
    private SightController sightController;

    private JacksonTester<List<Sight>> jsonSight;

    private JacksonTester<City> jsonCity;

    private JacksonTester<Sight> jsonOne;

    @Before
    public void setup()
    {
        JacksonTester.initFields(this,new ObjectMapper());

        mvc = MockMvcBuilders.standaloneSetup(sightController)
                .build();
    }

    @Test
    public void canFindByCityName() throws Exception
    {
        UUID uuid = UUID.randomUUID();
        City city = new City("Athens",0.0,0.0,"");
        List<Sight> sights = new ArrayList<>();
        Sight sight = new Sight("Acropolis",0.0,0.0,"","",1,city);
        sights.add(sight);
        given(sightSRepository.findByCity_Name("Athens"))
                .willReturn((sights));

        MockHttpServletResponse response = mvc.perform(
                get("/cities/"+city.getName()+"/sights")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(),jsonSight.write(sights).getJson());


    }
    @Test
    public void canSaveSight() throws  Exception
    {
        UUID uuid = UUID.randomUUID();
        City city = new City("Athens",0.0,0.0,"");
        city.setCityId(uuid);

        mvc.perform(
                post("/cities").contentType(MediaType.APPLICATION_JSON).content(
                        jsonCity.write(city).getJson()
                )).andReturn().getResponse();

        MockHttpServletResponse response = mvc.perform(
                post("/cities/"+city.getCityId()+"/sights").contentType(MediaType.APPLICATION_JSON).content(
                        jsonOne.write(new Sight("Acropolis",0.0,0.0)).getJson()
                )).andReturn().getResponse();

        assertEquals(response.getStatus(),HttpStatus.OK.value());
    }

    @Test
    public void shouldHaveEmptyDB() throws Exception
    {
        mvc.perform(get("/cities/Paris/sights")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(0)));
    }
}
