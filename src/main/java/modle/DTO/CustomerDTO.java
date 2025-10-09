package modle.DTO;

import lombok.*;



public class CustomerDTO {
    private String cusID;
    private String title;
    private String cusName;
    private String cusAddress;
    private String dob;
    private String cusSalary;
    private String city;
    private String province;
    private String postalCode;

    public CustomerDTO(String title, String cusID, String cusName, String cusAddress, String dob, String cusSalary, String city, String province, String postalCode) {
        this.title = title;
        this.cusID = cusID;
        this.cusName = cusName;
        this.cusAddress = cusAddress;
        this.dob = dob;
        this.cusSalary = cusSalary;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    public String getCusID() {
        return cusID;
    }

    public String getTitle() {
        return title;
    }

    public String getCusName() {
        return cusName;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public String getDob() {
        return dob;
    }

    public String getCusSalary() {
        return cusSalary;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setCusSalary(String cusSalary) {
        this.cusSalary = cusSalary;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "cusID='" + cusID + '\'' +
                ", title='" + title + '\'' +
                ", cusName='" + cusName + '\'' +
                ", cusAddress='" + cusAddress + '\'' +
                ", dob='" + dob + '\'' +
                ", cusSalary='" + cusSalary + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
