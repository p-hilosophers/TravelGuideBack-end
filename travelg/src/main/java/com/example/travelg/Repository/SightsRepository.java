package com.example.travelg.Repository;

import com.example.travelg.Model.Sight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SightsRepository extends JpaRepository<Sight, UUID> {

    Page<Sight> findByCity_CityId(UUID cityId, Pageable pageable);
}
