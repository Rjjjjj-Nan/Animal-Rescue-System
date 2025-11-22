package models;

public class Employees extends Applicants{

    private int applicantId;
    private String username;
    private String password;
    private String startDate;
    private String workStatus;

    public Employees(String firstName, String lastName, int age, String address, String contact, String[] skills, int applicantId, String username, String startDate) {
        super(firstName, lastName, age, address, contact, skills);
        this.applicantId = applicantId;
        this.username = username;
        this.password = "Employee123";
        this.startDate = startDate;
        this.workStatus = "Active";
    }

    public Employees(int applicantId, String username, String startDate) {
        this.applicantId = applicantId;
        this.username = username;
        this.password = "Employee123";
        this.startDate = startDate;
    }

    public Employees() {
        //empty
    }

    public int getApplicantId() {
        return applicantId;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getStartDate() {
        return startDate;
    }
    public String getWorkStatus() {
        return workStatus;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setWorkStatus(String status) {
        this.workStatus = status;
    }

    @Override
    public String toString() {
        return "Name: " + getFirstName() + " " + getLastName() + " | Status: " + getStatus() + " | Aplicant Id: " + applicantId + " | Username: " + username 
        + " | Password: " + password + " | Start Date: " + startDate;
    }
    
}
