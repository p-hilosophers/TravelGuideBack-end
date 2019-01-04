package com.travelg.Controller;

import com.travelg.AbstractTest;
import com.travelg.Model.City;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PhotoControllerTest extends AbstractTest {



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

   @Test
    public void getPhotosList() throws Exception
    {
        String uri = "/sights/1/photos";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        City[] citieslist = super.mapFromJson(content, City[].class);
        assertTrue(citieslist.length > 0);


    }
}
