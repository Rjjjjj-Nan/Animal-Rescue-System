package models;

import handlers.RehomeJsonHandler;

import java.util.List;

public class RehomingRequest extends Generator{
    private int rehomingId;
    private String username;
    private String name;
    private String animalType;
    private String breed;
    private String sex;
    private String age;
    private String reason;
    private String status; // Pending / Approved / Denied

    public RehomingRequest(String username, String name, String animalType, String breed,
                           String sex, String age, String reason) {
        this.rehomingId = idGenerator();
        this.username = username;
        this.name = name;
        this.animalType = animalType;
        this.breed = breed;
        this.sex = sex;
        this.age = age.isBlank() ? "N/A" : age;
        this.reason = reason;
        this.status = "Pending";
    }

    public RehomingRequest() {}

    public String getUsername() { return username; }
    public String getName() { return name; }
    public String getAnimalType() { return animalType; }
    public String getBreed() { return breed; }
    public String getSex() { return sex; }
    public String getAge() { return age; }
    public String getReason() { return reason; }
    public String getStatus() { return status; }
    public int getRehomingId() { return rehomingId; }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "User: " + username + " | Pet: " + name + " | Breed: " + breed +
               " | Status: " + status + " | Reason: " + reason;
    }

    @Override
    public int idGenerator() {
        List<RehomingRequest> id = RehomeJsonHandler.loadRequests();

        if (id.isEmpty()) {
            return 1000;
        }

        int maxId = id.stream().mapToInt(RehomingRequest::getRehomingId).max().orElse(1000);

        return maxId + 1;
    }
}
