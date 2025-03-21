package model;

public class StaffModel {
    private int staffId;
    private String fullName;
    private String gender;
    private String contactNo;
    private String staffType;

    // Default constructor
    public StaffModel() {
    }

    // Parameterized constructor
    public StaffModel(int staffId, String fullName, String gender, String contactNo, String staffType) {
        this.staffId = staffId;
        this.fullName = fullName;
        this.gender = gender;
        this.contactNo = contactNo;
        this.staffType = staffType;
    }

    // Getters and Setters
    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

    // For debugging or displaying object details
    @Override
    public String toString() {
        return "StaffModel [staffId=" + staffId + ", fullName=" + fullName + ", gender=" + gender + ", contactNo=" + contactNo + ", staffType=" + staffType + "]";
    }
}
