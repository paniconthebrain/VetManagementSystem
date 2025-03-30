package model;

import java.time.LocalDate;
import java.util.Date;

public class CompanySetupModel {

	private Integer companyId;
	private String companyRegisteredCode;
	private String companyName;
	private String companyAddress;
	private String companyContactNo;
	private LocalDate companyRegisteredDate;
	private String companyLogoPath;

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyRegisteredCode() {
		return companyRegisteredCode;
	}

	public void setCompanyRegisteredCode(String companyRegisteredCode) {
		this.companyRegisteredCode = companyRegisteredCode;
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

	public LocalDate getCompanyRegisteredDate() {
		return companyRegisteredDate;
	}

	public void setCompanyRegisteredDate(LocalDate companyRegisteredDate) {
		this.companyRegisteredDate = companyRegisteredDate;
	}

	public String getCompanyLogoPath() {
		return companyLogoPath;
	}

	public void setCompanyLogoPath(String companyLogoPath) {
		this.companyLogoPath = companyLogoPath;
	}

	public CompanySetupModel(Integer companyId, String companyRegisteredCode, String companyName, String companyAddress,
			String companyContactNo, LocalDate companyRegisteredDate, String companyLogoPath) {
		this.companyId = companyId;
		this.companyRegisteredCode = companyRegisteredCode;
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.companyContactNo = companyContactNo;
		this.companyRegisteredDate = companyRegisteredDate;
		this.companyLogoPath = companyLogoPath;
	}

	public CompanySetupModel() {
		this.companyId = 0;
		this.companyRegisteredCode = "";
		this.companyName = "";
		this.companyAddress = "";
		this.companyContactNo = "";
		this.companyRegisteredDate = null;
		this.companyLogoPath = "";
	}

	public CompanySetupModel(CompanySetupModel companySetupModel) {
		this.companyId = companySetupModel.companyId;
		this.companyRegisteredCode = companySetupModel.companyRegisteredCode;
		this.companyName = companySetupModel.companyName;
		this.companyAddress = companySetupModel.companyAddress;
		this.companyContactNo = companySetupModel.companyContactNo;
		this.companyRegisteredDate = companySetupModel.companyRegisteredDate;
		this.companyLogoPath = companySetupModel.companyLogoPath;
	}

}
