package models;

import java.util.List;

import handlers.AdoptionInheritJsonHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdoptionLogs extends AdoptionApplication implements Generator{ 
    
    private String date;

    public AdoptionLogs() {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date = now.format(formatter);
    }

    public AdoptionLogs(int applicationId, String username, int animalId, String status) {
        super(applicationId, username, animalId, status);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date = now.format(formatter);
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Application Id: " + getApplicationId() + " | Adopter: " + getUsername() + " | Animal Id: " + getAnimalId() + " | Status: " + getStatus() + "Date: " + date;
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
