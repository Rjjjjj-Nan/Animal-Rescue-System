package models;

import java.util.Arrays;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Applicants {
    
    private String firstName;
    private String lastName;
    private int age;
    private String address;
    private String contact;
    private String[] skills;
    private String date;
    private String status; //for evaluation, for interview, Hired

    public Applicants(String firstName, String lastName, int age, String address, String contact, String[] skills) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.contact = contact;
        this.skills = skills;
        this.status = "For Evaluation";

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = now.format(formatter);
    }

    public Applicants() {
        //empty
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getAge() {
        return age;
    }
    public String getAddress() {
        return address;
    }
    public String getContact() {
        return contact;
    }
    public String[] getSkills() {
        return skills;
    }
    public String getDate() {
        return date;
    }
    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "First Name: " + firstName + " | Last Name: " + lastName + " | Age: " + age + " | Address: " + address
         + " | Contact: " + contact + " | Skills: " + Arrays.toString(skills) + "Status: " + status + " | Date: " + date;
    }


}
