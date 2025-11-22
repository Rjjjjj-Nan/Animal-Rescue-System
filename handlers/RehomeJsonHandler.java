package handlers;

import models.RehomingRequest;

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

public class RehomeJsonHandler {
    private static final String FILE = "rehoming_requests.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<RehomingRequest> loadRequests() {
        try (Reader reader = new FileReader(FILE)) {
            Type listType = new TypeToken<List<RehomingRequest>>() {}.getType();
            List<RehomingRequest> requests = gson.fromJson(reader, listType);
            return requests != null ? requests : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void saveRequests(List<RehomingRequest> requests) {
        try (Writer writer = new FileWriter(FILE)) {
            gson.toJson(requests, writer);
        } catch (IOException e) {
            System.out.println("Failed to save rehoming requests: " + e.getMessage());
        }
    }

    public static void addRequest(RehomingRequest request) {
        List<RehomingRequest> requests = loadRequests();
        requests.add(request);
        saveRequests(requests);
    }

    public static List<RehomingRequest> getUserRequests(String username) {
        List<RehomingRequest> all = loadRequests();
        List<RehomingRequest> filtered = new ArrayList<>();
        for (RehomingRequest req : all) {
            if (req.getUsername().equals(username)) {
                filtered.add(req);
            }
        }
        return filtered;
    }
}
