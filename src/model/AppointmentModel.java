package model;

public class AppointmentModel {
	
	private Integer appointmentId;
	private String customerName;
	private String Remarks;
	private String appointmentDate;
	
	public Integer getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getRemarks() {
		return this.Remarks;
	}
	public void setRemarks(String remarks) {
		this.Remarks = remarks;
	}
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	
	//GETTER AND SETTER
	public AppointmentModel(Integer appointmentId, String customerName, String remarks, String appointmentDate) {
		this.appointmentId = appointmentId;
		this.customerName = customerName;
		this.Remarks = remarks;
		this.appointmentDate = appointmentDate;
	}
	
	public AppointmentModel() {
		this.appointmentId = 0;
		this.customerName = "";
		this.Remarks = "";
		this.appointmentDate = "";
	}
	
	public AppointmentModel(AppointmentModel appointmentModel) {
		this.appointmentId = appointmentModel.appointmentId;
		this.customerName = appointmentModel.customerName;
		this.Remarks = appointmentModel.Remarks;
		this.appointmentDate = appointmentModel.appointmentDate;
	}

}
