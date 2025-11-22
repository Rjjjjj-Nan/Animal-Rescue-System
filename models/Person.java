package models;

public class Person {
    private String fullName;
    private int age;
    private String address;
    private String contactNo;

    public Person(String fullName, int age, String address, String contactNo) {
        this.fullName = fullName;
        this.age = age;
        this.address = address;
        this.contactNo = contactNo;
    }

    public Person() {}

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}
