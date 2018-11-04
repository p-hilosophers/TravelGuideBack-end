package com.example.travelg.Repository;

import com.example.travelg.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {


    User findByEmailAndPassword(String email,String password);

    Optional findByName(String name);
}
