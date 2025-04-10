package model;

public class ClientFollowUpModel {
	private int followUpId;
	private int ownerId;
	private String customerName;
	private String followUpType;
	private String followUpDate;
	private String remarks;

	public int getFollowUpId() {
		return followUpId;
	}

	public void setFollowUpId(int followUpId) {
		this.followUpId = followUpId;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getFollowUpType() {
		return followUpType;
	}

	public void setFollowUpType(String followUpType) {
		this.followUpType = followUpType;
	}

	public String getFollowUpDate() {
		return followUpDate;
	}

	public void setFollowUpDate(String followUpDate) {
		this.followUpDate = followUpDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
