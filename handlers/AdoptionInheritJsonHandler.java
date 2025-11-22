package handlers;

import models.AdoptionLogs;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class AdoptionInheritJsonHandler {
    private static final String file = "AdoptionLogs.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<AdoptionLogs> loadAdoptionLogs() {
        try (FileReader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<AdoptionLogs>>() {}.getType();
            List<AdoptionLogs> logs = gson.fromJson(reader, listType);
            return logs != null ? logs : new ArrayList<>();
        }
        catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void saveAdoption(List<AdoptionLogs> logs) {
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(logs, writer);
        }
        catch (IOException e) {
            System.out.println("The currend adoption logs did not save. " + e.getMessage());
        }
    }

    public static void addAdoption(AdoptionLogs logs) {
        List<AdoptionLogs> data = loadAdoptionLogs();
        data.add(logs);
        saveAdoption(data);
    }

    public static List<AdoptionLogs> getAdoptionLogs(String username) {
        List<AdoptionLogs> logs = loadAdoptionLogs();
        List<AdoptionLogs> filter = new ArrayList<>();

        for (AdoptionLogs adopt : logs) {
            if (adopt.getUsername().equals(username)) {
                filter.add(adopt);
            }
        }
        return filter;
    }

    public static List<AdoptionLogs> getAdoptionLogs() {
        List<AdoptionLogs> logs = loadAdoptionLogs();
        List<AdoptionLogs> filter = new ArrayList<>();

        for (AdoptionLogs adopt : logs) {
            filter.add(adopt);
        }
        return filter;
    }

}
