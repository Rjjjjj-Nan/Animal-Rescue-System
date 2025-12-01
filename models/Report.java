package models;

import java.util.List;

import handlers.ReportJsonHandler;

public class Report extends Generator{
    private String username;
    private String type;
    private String description;
    private String location;
    private String status; // In-Review / Resolved / Archived
    private String priority; // Low / Med / High / Urgent

    public Report(String username, String type, String description, String location) {
        this.username = username;
        this.type = type;
        this.description = description;
        this.location = location;
        this.status = "In-Review";
        this.priority = "Unassigned yet";
    }

    public Report() {}

    public String getUsername() { return username; }
    public String getType() { return type; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public String getStatus() { return status; }
    public String getPriority() { return priority; }

    public void setStatus(String status) { this.status = status; }
    public void setPriority(String priority) { this.priority = priority; }

    @Override
    public String toString() {
        return "User: " + username + " | Type: " + type + " | Location: " + location +
               " | Status: " + status + " | Priority: " + priority;
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
