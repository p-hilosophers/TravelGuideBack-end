package com.example.travelg.Controller;

import com.example.travelg.Exception.ResourceNotFoundException;
import com.example.travelg.Model.Sight;
import com.example.travelg.Repository.CityRepository;
import com.example.travelg.Repository.SightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class SightController {

    @Autowired
    private SightsRepository sightsRepository;

    @Autowired
    private CityRepository cityRepository;

    @GetMapping("/cities/{cityName}/sights")
    public List<Sight> getSightsByCityName(@PathVariable(value = "cityName") String cityName)
    {
        return  sightsRepository.findByCity_Name(cityName);
    }

 /*   @GetMapping("/cities/{cityId}/sights")
    public Page<Sight> getSightsByCityId(@PathVariable(value = "cityId") UUID cityId,
                                         Pageable pageable)
    {
        return sightsRepository.findByCity_CityId(cityId,pageable);
    }*/

    @PostMapping("/cities/{cityId}/sights")
    public Sight addSight(@PathVariable UUID cityId,
                          @Valid @RequestBody Sight sight)
    {
        return cityRepository.findById(cityId)
                .map(city -> {
                    sight.setCity(city);
                    return sightsRepository.save(sight);
                }).orElseThrow(()-> new ResourceNotFoundException("City not found with id"+cityId));
    }
    @PutMapping("/cities/{cityId}/sights/{sightId}")
    public Sight updateSight(@PathVariable UUID cityId,
                             @PathVariable UUID sightId,
                             @Valid @RequestBody Sight sightRequest)
    {
        if (!cityRepository.existsById(cityId))
        {
            throw new ResourceNotFoundException("City not found with id "+cityId);

        }
        return sightsRepository.findById(sightId)
                .map(sight -> {
                    sight.setName(sightRequest.getName());
                    sight.setLatitude(sightRequest.getLatitude());
                    sight.setLongitude(sightRequest.getLongitude());
                    sight.setPhotoCount(sightRequest.getPhotoCount());
                    sight.setSeason(sightRequest.getSeason());
                    sight.setCity(sightRequest.getCity());
                    return sightsRepository.save(sight);
                }).orElseThrow(()-> new ResourceNotFoundException("Sight not found with id" +sightId));
    }
    @DeleteMapping("/cities/{cityId}/sights/{sightId}")
    public ResponseEntity<?> deletePhoto(@PathVariable UUID cityId,
                                         @PathVariable UUID sightId)
    {
        if (!cityRepository.existsById(cityId))
        {
            throw new ResourceNotFoundException("City not found with id "+cityId);

        }
        return sightsRepository.findById(sightId)
                .map(sight -> {
                    sightsRepository.delete(sight);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()->new ResourceNotFoundException("Photo not found with id"+sightId ));
    }
}
