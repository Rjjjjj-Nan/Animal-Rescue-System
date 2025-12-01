package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportLogs extends Report {
    private String date;
    private String time;

    public ReportLogs() {
        LocalDateTime now = LocalDateTime.now();
        this.date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public ReportLogs(String username, String type, String description, String location) {
        super(username, type, description, location);
        LocalDateTime now = LocalDateTime.now();
        this.date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        super.getReportId();
    }

    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "User: " + getUsername() + " | Type: " + getType() + " | Location: " + getLocation() +
               " | Status: " + getStatus() + " | Priority: " + getPriority() + " | Date: " + date + " | Time: " + time;
    }
}
