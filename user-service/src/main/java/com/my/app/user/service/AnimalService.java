package com.my.app.user.service;

import com.my.app.user.dao.AnimalRepository;
import com.my.app.user.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository animalRepository;

    public List<Animal> findNearbyAnimals(double latitude, double longitude, int maxDistance) {
        return animalRepository.findNearbyAnimals(latitude, longitude, maxDistance);
    }
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    public Animal addAnimal(Animal animal) {
        return animalRepository.save(animal);
    }
}
