package handlers;

import models.Applicants;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ApplicantJsonHandler {
    private static final String file = "Applicants.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<Applicants> loadApplicants() {
        try (FileReader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<Applicants>>() {}.getType();
            List<Applicants> logs = gson.fromJson(reader, listType);
            return logs != null ? logs : new ArrayList<>();
        }
        catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void saveApplicants(List<Applicants> logs) {
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(logs, writer);
        }
        catch (IOException e) {
            System.out.println("The currend adoption logs did not save. " + e.getMessage());
        }
    }

    public static void addApplicants(Applicants logs) {
        List<Applicants> data = loadApplicants();
        data.add(logs);
        saveApplicants(data);
    }

    public static List<Applicants> getApplicants() {
        List<Applicants> logs = loadApplicants();
        List<Applicants> filter = new ArrayList<>();

        for (Applicants adopt : logs) {
            filter.add(adopt);
        }
        return filter;
    }
}
