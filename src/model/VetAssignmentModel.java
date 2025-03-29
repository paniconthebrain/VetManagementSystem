package model;

public class VetAssignmentModel {
    
	private int vetAssignId;
	private int ownerId;
	private int staffId;
	private String additionalRemarks;
	private String assignedDate;
	public int getVetAssignId() {
		return vetAssignId;
	}
	public void setVetAssignId(int vetAssignId) {
		this.vetAssignId = vetAssignId;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public String getAdditionalRemarks() {
		return additionalRemarks;
	}
	public void setAdditionalRemarks(String additionalRemarks) {
		this.additionalRemarks = additionalRemarks;
	}
	public String getAssignedDate() {
		return assignedDate;
	}
	public void setAssignedDate(String assignedDate) {
		this.assignedDate = assignedDate;
	}
	
	
	public VetAssignmentModel(int vetAssignId, int ownerId, int staffId, String additionalRemarks,
			String assignedDate) {
		this.vetAssignId = vetAssignId;
		this.ownerId = ownerId;
		this.staffId = staffId;
		this.additionalRemarks = additionalRemarks;
		this.assignedDate = assignedDate;
	}
	
	public VetAssignmentModel() {
		this.vetAssignId = 0;
		this.ownerId = 0;
		this.staffId = 0;
		this.additionalRemarks = "";
		this.assignedDate = "";
	}
	
	public VetAssignmentModel(VetAssignmentModel vm) {
		this.vetAssignId = vm.vetAssignId;
		this.ownerId = vm.ownerId;
		this.staffId = vm.staffId;
		this.additionalRemarks = vm.additionalRemarks;
		this.assignedDate = vm.assignedDate;
	}

	
	
	
	
}
