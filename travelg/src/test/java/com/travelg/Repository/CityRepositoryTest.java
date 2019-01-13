package com.travelg.Repository;

import com.travelg.Model.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


import javax.transaction.Transactional;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void whenFindByName_thenReturnCity()
    {
        City berlin = new City();
        berlin.setName("Berlin");
        entityManager.persist(berlin);
        entityManager.flush();




        City found = cityRepository.findByName(berlin.getName());

        assertThat(found.getName()).isEqualTo(berlin.getName());
    }
}
