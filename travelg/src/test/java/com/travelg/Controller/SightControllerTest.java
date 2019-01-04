package com.travelg.Controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SightControllerTest {

    @Autowired
    private SightController sightController;

    @Test
    public void controllerInitializedCorrectly() {

        assertThat(sightController).isNotNull();
    }
}
