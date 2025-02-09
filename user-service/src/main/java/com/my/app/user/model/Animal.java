package com.my.app.user.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "animals")
public class Animal {

    @Id
    private String id;
    private String name;
    private String type;
    private String breed;
    private int age;
    private String status;
    private GeoJsonPoint location;

    // Default Constructor
    public Animal() {
    }

    // Parameterized Constructor
    public Animal(String name, String type, String breed, int age, String status, GeoJsonPoint location) {
        this.name = name;
        this.type = type;
        this.breed = breed;
        this.age = age;
        this.status = status;
        this.location = location;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
}
