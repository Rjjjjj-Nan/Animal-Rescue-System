package handlers;

import models.ReportLogs;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ReportJsonHandler {
    private static final String file = "UserReports.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<ReportLogs> loadUserReports() {
        try (FileReader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<ReportLogs>>() {}.getType();
            List<ReportLogs> logs = gson.fromJson(reader, listType);
            return logs != null ? logs : new ArrayList<>();
        }
        catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void saveReportLogs(List<ReportLogs> logs) {
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(logs, writer);
        }
        catch (IOException e) {
            System.out.println("The current rescue log did not save. " + e.getMessage());
        }
    }

    public static void addReportLogs(ReportLogs logs) {
        List<ReportLogs> data = loadUserReports();
        data.add(logs);
        saveReportLogs(data);
    }

    public static List<ReportLogs> getReportLogs() {
        List<ReportLogs> logs = loadUserReports();
        List<ReportLogs> filter = new ArrayList<>();

        for (ReportLogs rescue : logs) {
            filter.add(rescue);
        }
        return filter;
    }
}
