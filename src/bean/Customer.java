package bean;

import java.sql.Date;

public class Customer implements java.io.Serializable {

	//フィールド変数
		private int customerCode;
		private String customerName1;
		private String customerName2;
		private Date inBirthday; //INSERT用
		private String outBirthday; //SELECT用
		private String gender;
		private String tel;
		private String mail;
		private String postalcode;
		private String address;
		private String work;
		private String color;


		//getter
		public int getCustomerCode() {
			return customerCode;
		}

		public String getCustomerName1() {
			return customerName1;
		}

		public String getCustomerName2() {
			return customerName2;
		}

		public Date getInBirthday() {
			return inBirthday;
		}

		public String getGender() {
			return gender;
		}

		public String getTel() {
			return tel;
		}

		public String getMail() {
			return mail;
		}

		public String getPostalcode() {
			return postalcode;
		}

		public String getAddress() {
			return address;
		}

		public String getWork() {
			return work;
		}

		public String getColor() {
			return color;
		}

		public String getOutBirthday() {
			return outBirthday;
		}


		//setter
		public void setCustomerCode(int customerCode) {
			this.customerCode = customerCode;
		}

		public void setCustomerName1(String customerName1) {
			this.customerName1 = customerName1;
		}

		public void setCustomerName2(String customerName2) {
			this.customerName2 = customerName2;
		}

		public void setInBirthday(Date birthday) {
			this.inBirthday = birthday;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}

		public void setMail(String mail) {
			this.mail = mail;
		}

		public void setPostalcode(String postalcode) {
			this.postalcode = postalcode;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public void setWork(String work) {
			this.work = work;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public void setOutBirthday(String outBirthday) {
			this.outBirthday = outBirthday;
		}

}
