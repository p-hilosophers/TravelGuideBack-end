package com.example.travelg.ControllerTest;

import com.example.travelg.AbstractTest;
import com.example.travelg.Controller.PhotoController;
import com.example.travelg.Model.Photo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static javax.xml.transform.OutputKeys.VERSION;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebMvcTest(PhotoController.class)
public class PhotoControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
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

 /*   @Test
    public void getPhotosList() throws Exception
    {
        Photo photo = new Photo();
        photo.setIdPhoto(UUID.fromString("1"));

        List<Photo> allPhotos = Collections.singletonList(photo);

        given(photoController.getPhotosByCityId(willReturn(allPhotos)));

        mvc.perform(get(VERSION + PHOTO + "all")
                .with(user)


    }*/
}
