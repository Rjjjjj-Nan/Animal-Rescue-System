package handlers;

import models.Admin;

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

public class AdminJsonHandler {
    
    private static final String FILE_PATH = "Admins.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<Admin> loadAdmins() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Admin>>() {}.getType();
            List<Admin> users = gson.fromJson(reader, listType);
            return users != null ? users : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void saveAdmins(List<Admin> admin) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(admin, writer);
        } catch (IOException e) {
            System.out.println("Failed to save users: " + e.getMessage());
        }
    }

    public static void addAdmin(Admin admin) {
        List<Admin> list = loadAdmins();
        list.add(admin);
        saveAdmins(list);
    }

    public static List<Admin> getAdmins() {
        List<Admin> all = loadAdmins();
        List<Admin> filtered = new ArrayList<>();
        for (Admin admin : all) {
            filtered.add(admin);
        }
        return filtered;
    }

}
