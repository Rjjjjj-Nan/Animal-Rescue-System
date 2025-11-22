package handlers;

import models.Employees;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class EmployeeJsonHandler {
    private static final String file = "Employee.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<Employees> loadEmployees() {
        try (FileReader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<Employees>>() {}.getType();
            List<Employees> logs = gson.fromJson(reader, listType);
            return logs != null ? logs : new ArrayList<>();
        }
        catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void saveEmployee(List<Employees> logs) {
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(logs, writer);
        }
        catch (IOException e) {
            System.out.println("The currend adoption logs did not save. " + e.getMessage());
        }
    }

    public static void addEmployee(Employees logs) {
        List<Employees> data = loadEmployees();
        data.add(logs);
        saveEmployee(data);
    }

    public static List<Employees> getAdoptionLogs() {
        List<Employees> logs = loadEmployees();
        List<Employees> filter = new ArrayList<>();

        for (Employees adopt : logs) {
            filter.add(adopt);
        }
        return filter;
    }
}
