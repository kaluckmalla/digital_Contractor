package com.springrest.springrest.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "land_upload")
public class LandUpload {
	
	
	@Id
	private String landId;//pk
	
	@Column(nullable = true)
	private long landOwnerPhone;//fk
	
	@Column(nullable = false)
	private String landTitle;//*
	
	@Column(nullable = false)
	private String price;//*
	
	@Column(nullable = false)
	private String area;//*
	
	@Column(nullable = false)
	private String areaUnit;//*
	
	@Column(nullable = true)
	private String facingDirection;
	
	@Column(nullable = false)
	private String province;//*
		
	@Column(nullable = false)
	private String district;//*
	
	@Column(nullable = false)
	private String administration;//*
	
	@Column(nullable = false)
	private String wardNo;//*
	
	@Column(nullable = true)
	private String tole;
	
	@Column(nullable = true)
	private String city;
	
	@Column(nullable = false)
	private String name;//*
	
	@Column(nullable = false)
	private String contactPhoneNumber;//*
	
	@Column(nullable =true)
	private String email;
	
	@Column(nullable = false)
	private String expiryDate;//*	
	
	@Column(nullable = true)
	private String date;
	
	@Column(nullable = true)
	private String imageName;
	
	
	@Transient//not creating column name in database but use in some calculation  to do
	private String encodedImage;


	public LandUpload() {
		super();
		// TODO Auto-generated constructor stub
	}


	public LandUpload(String landId, long landOwnerPhone, String landTitle, String price, String area, String areaUnit,
			String facingDirection, String province, String district, String administration, String wardNo, String tole,
			String city, String name, String contactPhoneNumber, String email, String expiryDate, String date,
			String imageName, String encodedImage) {
		super();
		this.landId = landId;
		this.landOwnerPhone = landOwnerPhone;
		this.landTitle = landTitle;
		this.price = price;
		this.area = area;
		this.areaUnit = areaUnit;
		this.facingDirection = facingDirection;
		this.province = province;
		this.district = district;
		this.administration = administration;
		this.wardNo = wardNo;
		this.tole = tole;
		this.city = city;
		this.name = name;
		this.contactPhoneNumber = contactPhoneNumber;
		this.email = email;
		this.expiryDate = expiryDate;
		this.date = date;
		this.imageName = imageName;
		this.encodedImage = encodedImage;
	}


	public String getLandId() {
		return landId;
	}


	public void setLandId(String landId) {
		this.landId = landId;
	}


	public long getLandOwnerPhone() {
		return landOwnerPhone;
	}


	public void setLandOwnerPhone(long landOwnerPhone) {
		this.landOwnerPhone = landOwnerPhone;
	}


	public String getLandTitle() {
		return landTitle;
	}


	public void setLandTitle(String landTitle) {
		this.landTitle = landTitle;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}


	public String getAreaUnit() {
		return areaUnit;
	}


	public void setAreaUnit(String areaUnit) {
		this.areaUnit = areaUnit;
	}


	public String getFacingDirection() {
		return facingDirection;
	}


	public void setFacingDirection(String facingDirection) {
		this.facingDirection = facingDirection;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getDistrict() {
		return district;
	}


	public void setDistrict(String district) {
		this.district = district;
	}


	public String getAdministration() {
		return administration;
	}


	public void setAdministration(String administration) {
		this.administration = administration;
	}


	public String getWardNo() {
		return wardNo;
	}


	public void setWardNo(String wardNo) {
		this.wardNo = wardNo;
	}


	public String getTole() {
		return tole;
	}


	public void setTole(String tole) {
		this.tole = tole;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}


	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getExpiryDate() {
		return expiryDate;
	}


	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getImageName() {
		return imageName;
	}


	public void setImageName(String imageName) {
		this.imageName = imageName;
	}


	public String getEncodedImage() {
		return encodedImage;
	}


	public void setEncodedImage(String encodedImage) {
		this.encodedImage = encodedImage;
	}


	@Override
	public String toString() {
		return "LandUpload [landId=" + landId + ", landOwnerPhone=" + landOwnerPhone + ", landTitle=" + landTitle
				+ ", price=" + price + ", area=" + area + ", areaUnit=" + areaUnit + ", facingDirection="
				+ facingDirection + ", province=" + province + ", district=" + district + ", administration="
				+ administration + ", wardNo=" + wardNo + ", tole=" + tole + ", city=" + city + ", name=" + name
				+ ", contactPhoneNumber=" + contactPhoneNumber + ", email=" + email + ", expiryDate=" + expiryDate
				+ ", date=" + date + ", imageName=" + imageName + ", encodedImage=" + encodedImage + "]";
	}

	
	

}
