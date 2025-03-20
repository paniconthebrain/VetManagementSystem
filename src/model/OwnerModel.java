package model;

public class OwnerModel {

	private int ownerId;
	private String fullName;
	private String lastName;
	private String contactNo;
	private String Email;
	private String Address;
	//private String pet;
	private String petNickName;
	private String petBread;
	private String Date;
	
	
	public OwnerModel() {

		this.ownerId = 0;
		this.fullName = "";
		this.lastName = "";
		this.contactNo = "";
		Email = "";
		Address = "";
		this.petNickName = "";
		this.petBread = "";
		Date = "";
	}
	
	public OwnerModel(OwnerModel ownerModel) {

		this.ownerId = ownerModel.ownerId;
		this.fullName = ownerModel.fullName;
		this.lastName = ownerModel.lastName;
		this.contactNo = ownerModel.contactNo;
		Email = ownerModel.Email;
		Address = ownerModel.Address;
		this.petNickName = ownerModel.petNickName;
		this.petBread = ownerModel.petBread;
		Date = ownerModel.Date;
	}
	
	
	public OwnerModel(int ownerId, String fullName, String lastName, String contactNo, String email, String address,
			String petNickName, String petBread, String date) {
		
		this.ownerId = ownerId;
		this.fullName = fullName;
		this.lastName = lastName;
		this.contactNo = contactNo;
		Email = email;
		Address = address;
		this.petNickName = petNickName;
		this.petBread = petBread;
		Date = date;
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
	public String getPetNickName() {
		return petNickName;
	}
	public void setPetNickName(String petNickName) {
		this.petNickName = petNickName;
	}
	public String getPetBread() {
		return petBread;
	}
	public void setPetBread(String petBread) {
		this.petBread = petBread;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	
	
	
	
	
	
}
