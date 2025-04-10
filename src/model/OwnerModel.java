package model;

import java.time.LocalDate;

public class OwnerModel {
    private int ownerId;           // Unique identifier for each owner (primary key)
    private String fullName;        // Owner's full name
    private String contactNo;       // Owner's contact number
    private String email;           // Owner's email address
    private String address;         // Owner's physical address
    private String petNickName;     // Nickname of the owner's pet
    private String petBreed;        // Breed of the pet
    private LocalDate dateOfBirth;     // Date of birth of the owner or pet (depending on requirement)

    // Default constructor
    public OwnerModel() {
    }

    // Parameterized constructor
    public OwnerModel(int ownerId, String fullName, String contactNo, String email, String address,
                      String petNickName, String petBreed, LocalDate dateOfBirth) {
        this.ownerId = ownerId;
        this.fullName = fullName;
        this.contactNo = contactNo;
        this.email = email;
        this.address = address;
        this.petNickName = petNickName;
        this.petBreed = petBreed;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters and Setters

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
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

    public String getPetNickName() {
        return petNickName;
    }

    public void setPetNickName(String petNickName) {
        this.petNickName = petNickName;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    // Override the toString method to display object details for debugging or display purposes
    @Override
    public String toString() {
        return "OwnerModel [ownerId=" + ownerId + ", fullName=" + fullName + ", contactNo=" + contactNo +
                ", email=" + email + ", address=" + address + ", petNickName=" + petNickName +
                ", petBreed=" + petBreed + ", dateOfBirth=" + dateOfBirth + "]";
    }
}
