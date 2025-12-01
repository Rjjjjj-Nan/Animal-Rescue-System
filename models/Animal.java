package models;

import java.util.List;

import handlers.AnimalJsonHandler;

public class Animal extends Generator{
    private int id;
    private String name;
    private String type;
    private String breed;
    private String sex;
    private String age;
    private String status; // e.g., Adoptable, Quarantine, Medical

    public Animal(int id, String name, String type, String breed, String sex, String age, String status) {
        this.id = id; 
        this.name = name;
        this.type = type;
        this.breed = breed;
        this.sex = sex;
        this.age = age;
        this.status = status;
    }

    public Animal(int id, String name, String type, String breed, String sex, String age) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.breed = breed;
        this.sex = sex;
        this.age = age;
    }

    public Animal() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getBreed() {
        return breed;
    }

    public String getSex() {
        return sex;
    }

    public String getAge() {
        return age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ID: " + id +
               " | Name: " + name +
               " | Type: " + type +
               " | Breed: " + breed +
               " | Sex: " + sex +
               " | Age: " + age +
               " | Status: " + status;
    }

    @Override
    public int idGenerator() {
        List<Animal> id = AnimalJsonHandler.loadAnimals();

        if (id.isEmpty()) {
            return 1;
        }

        int maxId = id.stream().mapToInt(Animal::getId).max().orElse(1);

        return maxId + 1;
    }
}
