package modle.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

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

}
