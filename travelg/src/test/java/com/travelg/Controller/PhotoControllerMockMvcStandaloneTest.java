package com.travelg.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelg.Model.City;
import com.travelg.Model.Photo;
import com.travelg.Model.Sight;
import com.travelg.Repository.PhotoRepository;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(MockitoJUnitRunner.class)
public class PhotoControllerMockMvcStandaloneTest {

    private MockMvc mvc;

    @Mock
    PhotoRepository photoRepository;

    @InjectMocks
    private  PhotoController photoController;

    private JacksonTester<List<Photo>> jsonPhoto;

    private JacksonTester<Sight> jsonSight;

    private JacksonTester<City> jsonCity;

    @Before
    public void setup()
    {
        JacksonTester.initFields(this,new ObjectMapper());

        mvc = MockMvcBuilders.standaloneSetup(photoController)
                .build();
    }

    @Test
    public void canFindBySightName() throws Exception
    {
        UUID uuid = UUID.randomUUID();
        City city = new City("Athens",0.0,0.0,"");
        List<Photo> photos = new ArrayList<>();
        Sight sight = new Sight("Acropolis",0.0,0.0,"","",1,city);
        Photo photo = new Photo("name",0.0,0.0,sight);
        photos.add(photo);

        given(photoRepository.findBySight_Name("Acropolis"))
                .willReturn((photos));

        MockHttpServletResponse response = mvc.perform(
                get("/sights/Acropolis/photos")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }
}
