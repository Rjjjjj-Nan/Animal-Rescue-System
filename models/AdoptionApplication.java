package models;

public class AdoptionApplication {
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

}
