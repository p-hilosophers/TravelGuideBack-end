package com.example.travelg.ControllerTest;

import com.example.travelg.AbstractTest;
import com.example.travelg.Controller.CityController;
import com.example.travelg.Model.City;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CityControllerTest extends AbstractTest {

    @Autowired
    private CityController cityController;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void controllerInitializedCorrectly() {

        assertThat(cityController).isNotNull();
    }

    @Test
    public void getCitiesList() throws Exception
    {
        String uri = "/cities";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        City[] citieslist = super.mapFromJson(content, City[].class);
        assertTrue(citieslist.length > 0);
    }

    @Test
    public void insertCity() throws  Exception
    {
        String uri = "/cities";
        City city = new City();
        city.setName("Athens");
        String inputJson = super.mapToJson(city);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
/*
    @Test
    public void updateCity() throws Exception
    {
        String uri = "/cities/1";
        City city = new City();
        city.setName("London");
        String inputJson = super.mapToJson(city);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

    }*/

}
