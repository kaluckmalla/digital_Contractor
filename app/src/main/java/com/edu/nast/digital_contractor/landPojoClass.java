package com.edu.nast.digital_contractor;

public class landPojoClass {
    private String landId;//pk

    private long landOwnerPhone;//fk

    private String landTitle;//*

    private String price;//*

    private String area;//*

    private String areaUnit;//*

    private String facingDirection;

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

    public landPojoClass() {
    }

    public landPojoClass(String landId, long landOwnerPhone, String landTitle, String price, String area, String areaUnit, String facingDirection, String province, String district, String administration, String wardNo, String tole, String city, String name, String contactPhoneNumber, String email, String expiryDate, String date) {
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
}
