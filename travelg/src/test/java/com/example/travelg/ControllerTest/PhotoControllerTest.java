package com.example.travelg.ControllerTest;

import com.example.travelg.AbstractTest;
import com.example.travelg.Controller.PhotoController;
import com.example.travelg.Model.Photo;
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
public class PhotoControllerTest extends AbstractTest {

    @Autowired
    private PhotoController photoController;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void controllerInitializedCorrectly() {

        assertThat(photoController).isNotNull();
    }

}
