package com.travelg.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelg.Model.Photo;
import com.travelg.Repository.PhotoRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class PhotoControllerMockMvcStandaloneTest {

    private MockMvc mvc;

    @Mock
    PhotoRepository photoRepository;

    @InjectMocks
    private  PhotoController photoController;

    private JacksonTester<Photo> jsonPhoto;

    @Before
    public void setup()
    {
        JacksonTester.initFields(this,new ObjectMapper());

        mvc = MockMvcBuilders.standaloneSetup(photoController)
                .build();
    }
}
