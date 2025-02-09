package com.my.app.user.controller;

import com.my.app.user.model.Animal;
import com.my.app.user.model.User;
import com.my.app.user.service.AnimalService;
import com.my.app.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private AnimalService animalService;
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        String fileUrl = userService.saveUserToS3(user);
        return ResponseEntity.ok("User stored successfully at: " + fileUrl);
    }
    @RequestMapping(value = "/user/test", method = RequestMethod.GET)
    public String createProduct() {
        return "user is saved successfully";
    }
    // Add an animal listing
    @PostMapping
    public ResponseEntity<Animal> addAnimal(@RequestBody Animal animal) {
        return ResponseEntity.ok(animalService.addAnimal(animal));
    }

}
