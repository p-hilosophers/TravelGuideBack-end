package com.example.travelg.Repository;


import com.example.travelg.Model.City;
import com.example.travelg.Model.Sight;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SightRepositoryTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SightsRepository sightsRepository;

    @Test
    public void whenFindByCityName_thenReturnSight()
    {
        City berlin = new City();
        berlin.setName("Berlin");
        entityManager.persist(berlin);
        entityManager.flush();

        Sight sight = new Sight();
        sight.setCity(berlin);
        sight.setName("Brandenburg gate");
        entityManager.persist(sight);
        entityManager.flush();

        List<Sight> found = sightsRepository.findByCity_Name(berlin.getName());

        assertThat(found.get(0).getCity().getName()).isEqualTo(berlin.getName());
    }



}
