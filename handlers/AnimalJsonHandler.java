package handlers;

import models.Animal;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class AnimalJsonHandler {
    private static final String FILE_PATH = "animals.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<Animal> loadAnimals() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Animal>>() {}.getType();
            List<Animal> animals = gson.fromJson(reader, listType);
            return animals != null ? animals : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void saveAnimals(List<Animal> animals) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(animals, writer);
        } catch (IOException e) {
            System.out.println("Failed to save animals: " + e.getMessage());
        }
    }

    public static void addAnimal(Animal animal) {
        List<Animal> animals = loadAnimals();
        animals.add(animal);
        saveAnimals(animals);
    }

    public static List<Animal> getAnimals() {
        List<Animal> animals = loadAnimals();
        List<Animal> filter = new ArrayList<>();

        for (Animal animal : animals) {
            filter.add(animal);
        }
        return filter;
    }
}
