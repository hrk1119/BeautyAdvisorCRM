package bean;

import java.sql.Date;

public class Support implements java.io.Serializable{

	//フィールド変数
	private int supportId;
	private int customerCode;
	private Date inDate; //INSERT用
	private String outDate; //SELECT用
	private int storeId;
	private String storeName;
	private int employeeId;
	private String employeeName;
	private String memo;

	//getter
	public int getsupportId() {
		return supportId;
	}

	public int getCustomerCode() {
		return customerCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public String getMemo() {
		return memo;
	}

	public String getOutDate() {
		return outDate;
	}

	public Date getInDate() {
		return inDate;
	}

	public int getStoreId() {
		return storeId;
	}

	//setter
	public void setSupportId(int supportId) {
		this.supportId = supportId;
	}

	public void setCustomerCode(int customerCode) {
		this.customerCode = customerCode;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public void setInDate(Date indate) {
		this.inDate = indate;
	}

	public void setOutDate(String outdate) {
		this.outDate = outdate;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

}
