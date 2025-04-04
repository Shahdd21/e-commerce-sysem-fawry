package entity;

public class Customer {

    private String fullName;
    private String username;
    private String phoneNumber;
    private String email;
    private String address;

    public Customer(){

    }

    public Customer(String fullName, String username, String phoneNumber, String email, String address) {
        this.fullName = fullName;
        this.username = username + System.currentTimeMillis()%100;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
