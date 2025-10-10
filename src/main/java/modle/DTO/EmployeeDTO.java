package modle.DTO;

import lombok.*;



public class EmployeeDTO {
    private String empID;
    private String empName;
    private String empNic;
    private String empDob;
    private String empPosition;
    private double empSalary;
    private String empnumber;
    private String empAddress;
    private String empJoinDate;
    private String empStatus;

    public String getEmpID() {
        return empID;
    }

    public String getEmpName() {
        return empName;
    }

    public String getEmpNic() {
        return empNic;
    }

    public String getEmpDob() {
        return empDob;
    }

    public String getEmpPosition() {
        return empPosition;
    }

    public double getEmpSalary() {
        return empSalary;
    }

    public String getEmpnumber() {
        return empnumber;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public String getEmpJoinDate() {
        return empJoinDate;
    }

    public String getEmpStatus() {
        return empStatus;
    }

    public EmployeeDTO(String empID, String empName, String empNic, String empDob, String empPosition, double empSalary, String empnumber, String empAddress, String empJoinDate, String empStatus) {
        this.empID = empID;
        this.empName = empName;
        this.empNic = empNic;
        this.empDob = empDob;
        this.empPosition = empPosition;
        this.empSalary = empSalary;
        this.empnumber = empnumber;
        this.empAddress = empAddress;
        this.empJoinDate = empJoinDate;
        this.empStatus = empStatus;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setEmpNic(String empNic) {
        this.empNic = empNic;
    }

    public void setEmpDob(String empDob) {
        this.empDob = empDob;
    }

    public void setEmpPosition(String empPosition) {
        this.empPosition = empPosition;
    }

    public void setEmpSalary(double empSalary) {
        this.empSalary = empSalary;
    }

    public void setEmpnumber(String empnumber) {
        this.empnumber = empnumber;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public void setEmpJoinDate(String empJoinDate) {
        this.empJoinDate = empJoinDate;
    }

    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "empID='" + empID + '\'' +
                ", empName='" + empName + '\'' +
                ", empNic='" + empNic + '\'' +
                ", empDob='" + empDob + '\'' +
                ", empPosition='" + empPosition + '\'' +
                ", empSalary=" + empSalary +
                ", empnumber='" + empnumber + '\'' +
                ", empAddress='" + empAddress + '\'' +
                ", empJoinDate='" + empJoinDate + '\'' +
                ", empStatus='" + empStatus + '\'' +
                '}';
    }
}

