package handlers;

import models.AdoptionApplication;

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

public class AdoptionJsonHandler { //possible na mawala na tong file na to gawa possible na inde magagamit
    private static final String FILE = "adoption_applications.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<AdoptionApplication> loadApplications() {
        try (Reader reader = new FileReader(FILE)) {
            Type listType = new TypeToken<List<AdoptionApplication>>() {}.getType();
            List<AdoptionApplication> list = gson.fromJson(reader, listType);
            return list != null ? list : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void saveApplications(List<AdoptionApplication> apps) {
        try (Writer writer = new FileWriter(FILE)) {
            gson.toJson(apps, writer);
        } catch (IOException e) {
            System.out.println("Failed to save applications: " + e.getMessage());
        }
    }

    public static void addApplication(AdoptionApplication app) {
        List<AdoptionApplication> list = loadApplications();
        list.add(app);
        saveApplications(list);
    }

    public static List<AdoptionApplication> getUserApplications(String username) {
        List<AdoptionApplication> all = loadApplications();
        List<AdoptionApplication> filtered = new ArrayList<>();
        for (AdoptionApplication app : all) {
            if (app.getUsername().equals(username)) {
                filtered.add(app);
            }
        }
        return filtered;
    }
}
