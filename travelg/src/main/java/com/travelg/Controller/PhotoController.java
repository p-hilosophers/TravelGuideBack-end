package com.travelg.Controller;

import com.travelg.Exception.ResourceNotFoundException;
import com.travelg.Model.Photo;
import com.travelg.Repository.PhotoRepository;
import com.travelg.Repository.SightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class PhotoController {

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private SightsRepository sightRepository;

    @GetMapping("/sights/{sightName}/photos")
    public List<Photo> getPhotosByCityId(@PathVariable(value = "sightName") String  sightName)
    {
        return photoRepository.findBySight_Name(sightName);
    }

    @PostMapping("/sights/{sightId}/photos")
    public Photo addPhoto(@PathVariable UUID sightId,
                          @Valid @RequestBody Photo photo)
    {
        return sightRepository.findById(sightId)
                .map(sight -> {
                    photo.setSight(sight);
                    return photoRepository.save(photo);
                }).orElseThrow(()-> new ResourceNotFoundException("Sight not found with id"+sightId));
    }
    @PutMapping("/sights/{sightId}/photos/{photoId}")
    public Photo updatePhoto(@PathVariable UUID sightId,
                             @PathVariable UUID photoId,
                             @Valid @RequestBody Photo photoRequest)
    {
        if (!sightRepository.existsById(sightId))
        {
            throw new ResourceNotFoundException("Sight not found with id"+sightId);

        }
        return  photoRepository.findById(photoId)
                .map(photo -> {
                    photo.setName(photoRequest.getName());
                    photo.setImageUrl(photoRequest.getImageUrl());
                    photo.setLatitude(photoRequest.getLatitude());
                    photo.setLongitude(photoRequest.getLongitude());
                    photo.setSight(photoRequest.getSight());
                    return  photoRepository.save(photo);
                }).orElseThrow(()->new ResourceNotFoundException("Photo not found with id"+photoId));
    }
    @DeleteMapping("/sights/{sightId}/photos/{photoId}")
    public ResponseEntity<?> deletePhoto(@PathVariable UUID sightId,
                                         @PathVariable UUID photoId)
    {
        if (!sightRepository.existsById(sightId))
        {
            throw new ResourceNotFoundException("Sight not found with id "+sightId);

        }
        return photoRepository.findById(photoId)
                .map(photo -> {
                    photoRepository.delete(photo);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()->new ResourceNotFoundException("Photo not found with id"+photoId));
    }

}
