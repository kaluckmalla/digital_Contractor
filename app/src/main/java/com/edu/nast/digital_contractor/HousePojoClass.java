package com.edu.nast.digital_contractor;

public class HousePojoClass {
    private String houseId;//pk

    private long houseOwnerPhone;//fk

    private String houseTitle;//*

    private String price;//*

    private String area;//*

    private String areaUnit;//*

    private String buildInArea;//*

    private String buildInAreaUnit;//*

    private String facingDirection;

    private String buildingAge;

    private String noOfFloors;//*

    private String totalRooms;//*

    private String bedRooms;

    private String bathRooms;

    private String kitchenRooms;

    private String province;//*

    private String district;//*

    private String administration;//*

    private String wardNo;//*

    private String tole;



    private String city;

    private String name;//*

    private String contactPhoneNumber;//*

    private String email;

    private String expiryDate;//*

    private String date;
    public HousePojoClass() {
    }

    public HousePojoClass(String houseId, long houseOwnerPhone, String houseTitle, String price, String area, String areaUnit, String buildInArea, String buildInAreaUnit, String facingDirection, String buildingAge, String noOfFloors, String totalRooms, String bedRooms, String bathRooms, String kitchenRooms, String province, String district, String administration, String wardNo, String tole, String city, String name, String contactPhoneNumber, String email, String expiryDate, String date) {
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

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public long getHouseOwnerPhone() {
        return houseOwnerPhone;
    }

    public void setHouseOwnerPhone(long houseOwnerPhone) {
        this.houseOwnerPhone = houseOwnerPhone;
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
}
