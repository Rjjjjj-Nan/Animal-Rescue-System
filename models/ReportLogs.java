package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import handlers.ReportJsonHandler;

public class ReportLogs extends Report implements Generator{
    private int reportId;
    private String date;
    private String time;

    public ReportLogs() {
        LocalDateTime now = LocalDateTime.now();
        this.date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        reportId++;
    }

    public ReportLogs(String username, String type, String description, String location) {
        super(username, type, description, location);
        LocalDateTime now = LocalDateTime.now();
        this.date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.reportId = idGenerator();
    }

    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }
    public int getReportId() {
        return reportId;
    }

    @Override
    public String toString() {
        return "User: " + getUsername() + " | Type: " + getType() + " | Location: " + getLocation() +
               " | Status: " + getStatus() + " | Priority: " + getPriority() + " | Date: " + date + " | Time: " + time;
    }

    @Override
    public int idGenerator() {
        List<ReportLogs> id = ReportJsonHandler.loadUserReports();

        if (id.isEmpty()) {
            return 100;
        }

        int maxId = id.stream().mapToInt(ReportLogs::getReportId).max().orElse(100);

        return maxId + 1;
    }
}
