package com.my.app.user.dao;


import com.my.app.user.model.Animal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AnimalRepository extends MongoRepository<Animal, String> {

    // Geospatial query to find animals near a specific location
    @Query("{ location: { $near: { $geometry: { type: 'Point', coordinates: [?1, ?0] }, $maxDistance: ?2 } } }")
    List<Animal> findNearbyAnimals(double latitude, double longitude, int maxDistance);
}
