package bean;

import java.sql.Date;

public class Product implements java.io.Serializable {

	//フィールド変数
	/**商品**/
	private String productId;
	private String colorNum;
	private String colorName;
	private String photo;
	/**商品グループ**/
	private int groupId;
	private String productName;
	private int inPrice; //INSERT用
	private String outPrice; //SERECT用
	private Date inDate; //INSERT用
	private String outDate; //SERECT用
	private String memo;
	private String situation;
	private String url;
	private String groupPhoto;
	/**ブランド**/
	private int brandId;
	private String brandName;
	/**メーカー**/
	private int manufacturerId;
	private String manufacturerName;
	/**カテゴリ**/
	private int categoryId;
	private String categoryName;
	/**パーソナルカラー**/
	private int pcId;
	private String pcName1;
	private String pcName2;
	/**カラーカテゴリ**/
	private int colorCategoryId;
	private String colorCategoryName;

	//setter
	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setColorNum(String colorNum) {
		this.colorNum = colorNum;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setOutDate(String date) {
		this.outDate = date;
	}

	public void setInDate(Date date) {
		this.inDate = date;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setPcName1(String pcName1) {
		this.pcName1 = pcName1;
	}

	public void setPcName2(String pcName2) {
		this.pcName2 = pcName2;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setColorCategoryId(int colorCategoryId) {
		this.colorCategoryId = colorCategoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public void setManufacturerId(int manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public void setPcId(int pcId) {
		this.pcId = pcId;
	}

	public void setColorCategoryName(String colorCategoryName) {
		this.colorCategoryName = colorCategoryName;
	}

	public void setOutPrice(String outPrice) {
		this.outPrice = outPrice;
	}

	public void setInPrice(int inPrice) {
		this.inPrice = inPrice;
	}

	public void setGroupPhoto(String groupPhoto) {
		this.groupPhoto = groupPhoto;
	}


	//getter
	public String getProductId() {
		return productId;
	}

	public String getColorNum() {
		return colorNum;
	}

	public String getColorName() {
		return colorName;
	}

	public int getGroupId() {
		return groupId;
	}

	public String getProductName() {
		return productName;
	}

	public Date getInDate() {
		return inDate;
	}

	public String getOutDate() {
		return outDate;
	}

	public String getMemo() {
		return memo;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public String getSituation() {
		return situation;
	}

	public String getBrandName() {
		return brandName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String getPcName1() {
		return pcName1;
	}

	public String getPcName2() {
		return pcName2;
	}

	public String getUrl() {
		return url;
	}

	public int getColorCategoryId() {
		return colorCategoryId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public int getBrandId() {
		return brandId;
	}

	public int getManufacturerId() {
		return manufacturerId;
	}

	public String getPhoto() {
		return photo;
	}

	public int getPcId() {
		return pcId;
	}

	public String getColorCategoryName() {
		return colorCategoryName;
	}

	public int getInPrice() {
		return inPrice;
	}

	public String getOutPrice() {
		return outPrice;
	}

	public String getGroupPhoto() {
		return groupPhoto;
	}

}
