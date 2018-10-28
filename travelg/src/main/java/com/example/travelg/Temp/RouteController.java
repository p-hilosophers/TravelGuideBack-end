/*
package com.example.travelg.Controller;

import com.example.travelg.Exception.ResourceNotFoundException;
import com.example.travelg.Repository.RouteRepository;
import com.example.travelg.Repository.UserRepository;
import com.example.travelg.Temp.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class RouteController {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/users/{userId}/routes")
    public List<Route> getRoutesByUserId(@PathVariable UUID userId) {
        return routeRepository.findByUserId(userId);
    }

    @PostMapping("/users/{userId}/routes")
    public Route addRoute(@PathVariable UUID userId,
                          @Valid @RequestBody Route route)
    {
        return userRepository.findById(userId)
                .map(user -> {
                    route.setUser(user);
                    return  routeRepository.save(route);
                }).orElseThrow(()-> new ResourceNotFoundException("User not found with id " + userId));
    }

    @PutMapping("/users/{userId}/routes/{routeId}")
    public Route updateRoute(@PathVariable UUID userId,
                             @PathVariable UUID routeId,
                             @Valid @RequestBody Route routeRequest) {
        if (!userRepository.existsById(userId))
        {
            throw new ResourceNotFoundException("User not found with id " + userId);
        }
        return  routeRepository.findById(routeId)
                .map(route -> {
                    route.setText(routeRequest.getText());
                    return routeRepository.save(route);
                }).orElseThrow(()->new ResourceNotFoundException("User not found with id " + userId));

    }

    @DeleteMapping("/users/{userId}/routes/{routeId}")
    public ResponseEntity<?> deleteRoute(@PathVariable UUID userId,
                                         @PathVariable UUID routeId)
    {
        if(!userRepository.existsById(userId))
        {
            throw new ResourceNotFoundException("User not found with id " + userId);
        }
        return  routeRepository.findById(routeId)
                .map(route -> {
                    routeRepository.delete(route);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()->new ResourceNotFoundException("User not found with id " + userId));
    }
}
*/
