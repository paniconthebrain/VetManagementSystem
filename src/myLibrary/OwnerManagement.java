package myLibrary;

public class OwnerManagement {

	private int ownerId;
	private String fullName;
	private String lastName;
	private String contactNo;
	private String Email;
	private String Address;
	//private String pet;
	
	public OwnerManagement() {
		this.ownerId = 0;
		this.fullName = "";
		this.lastName = "";
		this.contactNo = "";
		Email = "";
		Address = "";
	}
	
	public OwnerManagement(int ownerId, String fullName, String lastName, String contactNo, String email,
			String address) {
		this.ownerId = ownerId;
		this.fullName = fullName;
		this.lastName = lastName;
		this.contactNo = contactNo;
		Email = email;
		Address = address;
	}

	public OwnerManagement(OwnerManagement ownerManagement) {
		this.ownerId = ownerManagement.ownerId;
		this.fullName = ownerManagement.fullName;
		this.lastName = ownerManagement.lastName;
		this.contactNo = ownerManagement.contactNo;
		Email = ownerManagement.Email;
		Address = ownerManagement.Address;
	}

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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	@Override
	public String toString() {
		return "OwnerManagement [ownerId=" + ownerId + ", fullName=" + fullName + ", lastName=" + lastName
				+ ", contactNo=" + contactNo + ", Email=" + Email + ", Address=" + Address + "]";
	}
	
	
	
	
	
}
