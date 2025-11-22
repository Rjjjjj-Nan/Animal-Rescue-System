package handlers;

import models.ReportLogs;
import models.Report;

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

public class UserFileReportHandler {
    private static final String FILE = "reports.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<Report> loadReports() {
        try (Reader reader = new FileReader(FILE)) {
            Type listType = new TypeToken<List<Report>>() {}.getType();
            List<Report> reports = gson.fromJson(reader, listType);
            return reports != null ? reports : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void saveReports(List<Report> reports) {
        try (Writer writer = new FileWriter(FILE)) {
            gson.toJson(reports, writer);
        } catch (IOException e) {
            System.out.println("Failed to save reports: " + e.getMessage());
        }
    }

    public static void addReport(Report report) {
        List<Report> reports = loadReports();
        reports.add(report);
        saveReports(reports);
    
        ReportLogs logEntry = new ReportLogs(
            report.getUsername(),
            report.getType(),
            report.getDescription(),
            report.getLocation()
        );
        logEntry.setStatus(report.getStatus());
        logEntry.setPriority(report.getPriority());
        ReportJsonHandler.addReportLogs(logEntry);
    }
    public static List<Report> getUserReports(String username) {
        List<Report> all = loadReports();
        List<Report> filtered = new ArrayList<>();
        for (Report r : all) {
            if (r.getUsername().equals(username)) {
                filtered.add(r);
            }
        }
        return filtered;
    }
}
