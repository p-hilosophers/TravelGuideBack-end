package com.example.travelg.Repository;

import com.example.travelg.Model.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, UUID> {

   Page<Photo> findByCity_CityId( UUID cityId, Pageable pageable);
}
