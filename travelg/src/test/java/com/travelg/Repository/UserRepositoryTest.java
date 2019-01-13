package com.travelg.Repository;

import com.travelg.Model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testStoreUser()
    {

        User user = userRepository.save(new User(UUID.randomUUID(),"","","",""));

        assertThat(user).isNotNull();
        assertThat(userRepository.count()).isEqualTo(1L);
    }

    @Test
    public void whenFindByEmailAndPassword_thenReturnUser()
    {
        User user = new User();
        user.setEmail("user@");
        user.setPassword("password");
        entityManager.persist(user);
        entityManager.flush();




        User found = userRepository.findByEmailAndPassword(user.getEmail(),user.getPassword());

        assertThat(found.getEmail()).isEqualTo(user.getEmail());
        assertThat(found.getPassword()).isEqualTo(user.getPassword());
    }
}
