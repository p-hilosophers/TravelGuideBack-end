package com.travelg;

import com.travelg.ClusteringAlgorith.ClusteringResult;
import com.travelg.ClusteringAlgorith.ClusteringService;
import com.travelg.Model.City;
import com.travelg.Model.Sight;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integration.properties")
@Transactional
@Category(IntegrationTest.class)
public class RouteControllerIntegrationTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClusteringService service;

    @Test
    public void getRoutesTest() throws Exception {


        when(service.getRoutes("Berlin")).thenReturn(new ClusteringResult(
              Arrays.asList(Arrays.asList(new Sight("Brandenburg gate",0.0,0.0,"", "", 0,new City())))));

        ClusteringResult result = service.getRoutes("Berlin");
        mvc.perform(get("/routes/Berlin")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
              /* *//* .andExpect(jsonPath("$name",is("Brandenburg gate")));*//*
                .andExpect(jsonPath("$longitude",is(0.0)));*/

        Assert.assertEquals("Brandenburg gate",result.getRoutes().get(0).get(0).getName());
    }

}
