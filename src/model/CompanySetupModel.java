package model;

public class CompanySetupModel {
	
	private Integer companyId;
	private String companyName;
	private String companyAddress;
	private String companyContactNo;
	private String companyRegisteredDate;
	
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getCompanyContactNo() {
		return companyContactNo;
	}
	public void setCompanyContactNo(String companyContactNo) {
		this.companyContactNo = companyContactNo;
	}
	public String getCompanyRegisteredDate() {
		return companyRegisteredDate;
	}
	public void setCompanyRegisteredDate(String companyRegisteredDate) {
		this.companyRegisteredDate = companyRegisteredDate;
	}
	
	
	public CompanySetupModel(Integer companyId, String companyName, String companyAddress, String companyContactNo,
			String companyRegisteredDate) {
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.companyContactNo = companyContactNo;
		this.companyRegisteredDate = companyRegisteredDate;
	}
	
	public CompanySetupModel() {
		this.companyId = 0;
		this.companyName = "";
		this.companyAddress = "";
		this.companyContactNo = "";
		this.companyRegisteredDate = "";
	}
	
	public CompanySetupModel(CompanySetupModel companySetupModel) {
		this.companyId = companySetupModel.companyId;
		this.companyName = companySetupModel.companyName;
		this.companyAddress = companySetupModel.companyAddress;
		this.companyContactNo = companySetupModel.companyContactNo;
		this.companyRegisteredDate = companySetupModel.companyRegisteredDate;
	}
	
	
	
	

}
