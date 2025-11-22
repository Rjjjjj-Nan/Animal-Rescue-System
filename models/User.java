package models;

public class User extends Person {
    private String username;
    private String password;

    public User(String fullName, int age, String address, String contactNo,
                String username, String password) {
        super(fullName, age, address, contactNo);
        this.username = username;
        this.password = password;
    }

    public User() {
        // Required for Gson deserialization
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
