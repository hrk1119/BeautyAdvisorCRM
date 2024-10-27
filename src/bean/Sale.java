package bean;

import java.sql.Date;

public class Sale implements java.io.Serializable{

	//フィールド変数
	//saleテーブル
	private int saleId;
	private int customerCode;
	private int storeId;
	private Date inSaleDate; //INSERT用
	private String outSaleDate; //SELECT用

	//sale_detailテーブル
	private String productId;
	private int saleNum;

	private String colorNum;
	private String colorName;
	private String colorPhoto;
	private String productName;
	private String brandName;
	private String manufacturerName;

	private String storeName;

	private String outPrice;

	//setter
	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}

	public void setCustomerCode(int customerCode) {
		this.customerCode = customerCode;
	}

	public void setSaleNum(int saleNum) {
		this.saleNum = saleNum;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setColorNum(String colorNum) {
		this.colorNum = colorNum;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public void setColorPhoto(String colorPhoto) {
		this.colorPhoto = colorPhoto;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public void setOutSaleDate(String outSaleDate) {
		this.outSaleDate = outSaleDate;
	}

	public void setInSaleDate(Date inSaleDate) {
		this.inSaleDate = inSaleDate;
	}

	public void setOutPrice(String outPrice) {
		this.outPrice = outPrice;
	}


	//getter
	public int getSaleId() {
		return saleId;
	}

	public int getCustomerCode() {
		return customerCode;
	}

	public int getSaleNum() {
		return saleNum;
	}

	public String getStoreName() {
		return storeName;
	}

	public String getProductId() {
		return productId;
	}

	public String getColorNum() {
		return colorNum;
	}

	public String getColorName() {
		return colorName;
	}

	public String getColorPhoto() {
		return colorPhoto;
	}

	public String getProductName() {
		return productName;
	}

	public String getBrandName() {
		return brandName;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public int getStoreId() {
		return storeId;
	}

	public Date getInSaleDate() {
		return inSaleDate;
	}


	public String getOutSaleDate() {
		return outSaleDate;
	}

	public String getOutPrice() {
		return outPrice;
	}

}
