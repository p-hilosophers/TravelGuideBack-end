package com.travelg.Repository;


import com.travelg.Model.City;
import com.travelg.Model.Photo;
import com.travelg.Model.Sight;
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
public class PhotoRepositoryTest {


        @Autowired
        private PhotoRepository photoRepository;

        @Autowired
        private TestEntityManager entityManager;

        @Test
        public void testStorePhoto()
        {

            Photo photo = photoRepository.save(new Photo(" ",0.0,0.0));

            assertThat(photo).isNotNull();
            assertThat(photoRepository.count()).isEqualTo(1L);
        }

        @Test
        public void whenFindByCity_returnPhoto()
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

            Photo photo = new Photo();
            photo.setSight(sight);
            photo.setName("1");
            entityManager.persist(photo);
            entityManager.flush();

            List<Photo> found = photoRepository.findBySight_Name(sight.getName());
            assertThat(found.get(0).getSight().getName()).isEqualTo(sight.getName());

        }
}
