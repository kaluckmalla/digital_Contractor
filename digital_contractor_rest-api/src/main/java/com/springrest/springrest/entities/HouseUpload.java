package com.springrest.springrest.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "house_upload")
public class HouseUpload {

	@Id
	private String houseId;//pk
	
	@Column(nullable = true)
	private long houseOwnerPhone;//fk
	
	@Column(nullable = false)
	private String houseTitle;//*
	
	@Column(nullable = false)
	private String price;//*
	
	@Column(nullable = false)
	private String area;//*
	
	@Column(nullable = false)
	private String areaUnit;//*
	
	@Column(nullable = false)
	private String buildInArea;//*
	
	@Column(nullable = false)
	private String buildInAreaUnit;//*
	
	@Column(nullable = true)
	private String facingDirection;
	
	@Column(nullable = false)
	private String buildingAge;
	
	@Column(nullable = false)
	private String noOfFloors;//*
	
	@Column(nullable = false)
	private String totalRooms;//*
	
	@Column(nullable = true)
	private String bedRooms;
	
	@Column(nullable = true)
	private String bathRooms;
	
	@Column(nullable = true)
	private String kitchenRooms;
	
	
	
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
	
//default constructor
	
	public HouseUpload() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//constructor using field
		
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

	public HouseUpload(String houseId, long houseOwnerPhone, String houseTitle, String price, String area,
			String areaUnit, String buildInArea, String buildInAreaUnit, String facingDirection, String buildingAge,
			String noOfFloors, String totalRooms, String bedRooms, String bathRooms, String kitchenRooms, String imgId,
			String province, String district, String administration, String wardNo, String tole, String city,
			String name, String contactPhoneNumber, String email, String expiryDate, String date) {
		super();
		this.houseId = houseId;
		this.houseOwnerPhone = houseOwnerPhone;
		this.houseTitle = houseTitle;
		this.price = price;
		this.area = area;
		this.areaUnit = areaUnit;
		this.buildInArea = buildInArea;
		this.buildInAreaUnit = buildInAreaUnit;
		this.facingDirection = facingDirection;
		this.buildingAge = buildingAge;
		this.noOfFloors = noOfFloors;
		this.totalRooms = totalRooms;
		this.bedRooms = bedRooms;
		this.bathRooms = bathRooms;
		this.kitchenRooms = kitchenRooms;
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
		
	}


	
	//getter setter

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	
	public String getHouseTitle() {
		return houseTitle;
	}

	public void setHouseTitle(String houseTitle) {
		this.houseTitle = houseTitle;
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

	public String getBuildInArea() {
		return buildInArea;
	}

	public void setBuildInArea(String buildInArea) {
		this.buildInArea = buildInArea;
	}

	public String getBuildInAreaUnit() {
		return buildInAreaUnit;
	}

	public void setBuildInAreaUnit(String buildInAreaUnit) {
		this.buildInAreaUnit = buildInAreaUnit;
	}

	public String getFacingDirection() {
		return facingDirection;
	}

	public void setFacingDirection(String facingDirection) {
		this.facingDirection = facingDirection;
	}

	public String getBuildingAge() {
		return buildingAge;
	}

	public void setBuildingAge(String buildingAge) {
		this.buildingAge = buildingAge;
	}

	public String getNoOfFloors() {
		return noOfFloors;
	}

	public void setNoOfFloors(String noOfFloors) {
		this.noOfFloors = noOfFloors;
	}

	public String getTotalRooms() {
		return totalRooms;
	}

	public void setTotalRooms(String totalRooms) {
		this.totalRooms = totalRooms;
	}

	public String getBedRooms() {
		return bedRooms;
	}

	public void setBedRooms(String bedRooms) {
		this.bedRooms = bedRooms;
	}

	public String getBathRooms() {
		return bathRooms;
	}

	public void setBathRooms(String bathRooms) {
		this.bathRooms = bathRooms;
	}

	public String getKitchenRooms() {
		return kitchenRooms;
	}

	public void setKitchenRooms(String kitchenRooms) {
		this.kitchenRooms = kitchenRooms;
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
	//toString

	@Override
	public String toString() {
		return "HouseUpload [houseId=" + houseId + ", houseOwnerPhone=" + houseOwnerPhone + ", houseTitle=" + houseTitle
				+ ", price=" + price + ", area=" + area + ", areaUnit=" + areaUnit + ", buildInArea=" + buildInArea
				+ ", buildInAreaUnit=" + buildInAreaUnit + ", facingDirection=" + facingDirection + ", buildingAge="
				+ buildingAge + ", noOfFloors=" + noOfFloors + ", totalRooms=" + totalRooms + ", bedRooms=" + bedRooms
				+ ", bathRooms=" + bathRooms + ", kitchenRooms=" + kitchenRooms + ",, province="
				+ province + ", district=" + district + ", administration=" + administration + ", wardNo=" + wardNo
				+ ", tole=" + tole + ", city=" + city + ", name=" + name + ", contactPhoneNumber=" + contactPhoneNumber
				+ ", email=" + email + ", expiryDate=" + expiryDate + ", date=" + date + ", getHouseId()="
				+ getHouseId() + ", getHouseTitle()=" + getHouseTitle() + ", getPrice()=" + getPrice() + ", getArea()="
				+ getArea() + ", getAreaUnit()=" + getAreaUnit() + ", getBuildInArea()=" + getBuildInArea()
				+ ", getBuildInAreaUnit()=" + getBuildInAreaUnit() + ", getFacingDirection()=" + getFacingDirection()
				+ ", getBuildingAge()=" + getBuildingAge() + ", getNoOfFloors()=" + getNoOfFloors()
				+ ", getTotalRooms()=" + getTotalRooms() + ", getBedRooms()=" + getBedRooms() + ", getBathRooms()="
				+ getBathRooms() + ", getKitchenRooms()=" + getKitchenRooms() + ",  getProvince()=" + getProvince() + ", getDistrict()=" + getDistrict() + ", getAdministration()="
				+ getAdministration() + ", getWardNo()=" + getWardNo() + ", getTole()=" + getTole() + ", getCity()="
				+ getCity() + ", getName()=" + getName() + ", getContactPhoneNumber()=" + getContactPhoneNumber()
				+ ", getEmail()=" + getEmail() + ", getExpiryDate()=" + getExpiryDate() + ", getDate()=" + getDate()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	public long getHouseOwnerPhone() {
		return houseOwnerPhone;
	}

	public void setHouseOwnerPhone(long houseOwnerPhone) {
		this.houseOwnerPhone = houseOwnerPhone;
	}

	
	
	
	
}

