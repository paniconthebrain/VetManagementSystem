package model;

public class OwnerModel {
    private int ownerId;
    private String fullName;
    private String contactNo;
    private String email;
    private String address;
    private String petNickName;
    private String petBreed;
    private String dateOfBirth;

    public OwnerModel() {}

    public OwnerModel(int ownerId, String fullName, String contactNo, String email, String address,
                      String petNickName, String petBreed, String dateOfBirth) {
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "OwnerModel [ownerId=" + ownerId + ", fullName=" + fullName + ", contactNo=" + contactNo +
                ", email=" + email + ", address=" + address + ", petNickName=" + petNickName +
                ", petBreed=" + petBreed + ", dateOfBirth=" + dateOfBirth + "]";
    }
}
