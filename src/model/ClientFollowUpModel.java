package model;

public class ClientFollowUpModel {

	private Integer followUpId;
	private Integer customerId;
	private String customerName;
	private String followUpType;
	private String followUpDate;
	private String remarks;

	public Integer getFollowUpId() {
		return followUpId;
	}

	public void setFollowUpId(Integer followUpId) {
		this.followUpId = followUpId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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

	public ClientFollowUpModel(Integer followUpId, Integer customerId, String customerName, String followUpType,
			String followUpDate, String remarks) {
		this.followUpId = followUpId;
		this.customerId = customerId;
		this.customerName = customerName;
		this.followUpType = followUpType;
		this.followUpDate = followUpDate;
		this.remarks = remarks;
	}

	public ClientFollowUpModel() {
		this.followUpId = 0;
		this.customerId = 0;
		this.customerName = "";
		this.followUpType = "";
		this.followUpDate = "";
		this.remarks = "";
	}
	
	public ClientFollowUpModel(ClientFollowUpModel clientFollowUpModel) {
		this.followUpId = clientFollowUpModel.followUpId;
		this.customerId = clientFollowUpModel.customerId;
		this.customerName = clientFollowUpModel.customerName;
		this.followUpType = clientFollowUpModel.followUpType;
		this.followUpDate = clientFollowUpModel.followUpDate;
		this.remarks = clientFollowUpModel.remarks;
	}

}
