package models;

import java.util.List;

import handlers.AdoptionInheritJsonHandler;

public class AdoptionApplication extends Generator {
    private int applicationId;
    private String username;
    private int animalId;
    private String status; // Pending / Approved / Denied

    public AdoptionApplication(int applicationId, String username, int animalId, String status) {
        this.applicationId = applicationId;
        this.username = username;
        this.animalId = animalId;
        this.status = status;
    }

    public AdoptionApplication() {}

    public String getUsername() { return username; }
    public int getAnimalId() { return animalId; }
    public String getStatus() { return status; }
    public int getApplicationId() { return applicationId; }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "User: " + username + " | AnimalID: " + animalId + " | Status: " + status;
    }

    @Override
    public int idGenerator() {
        List<AdoptionLogs> id = AdoptionInheritJsonHandler.loadAdoptionLogs();

        if (id.isEmpty()) {
            return 100;
        }

        int maxId = id.stream().mapToInt(AdoptionLogs::getApplicationId).max().orElse(100);

        return maxId + 1;
    }

}
