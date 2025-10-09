package modle.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SupplierDTO {
    private String supplierId;
    private String supplierName;
    private String supplierCompany;
    private String supplierAddress;
    private String supplierCity;
    private String supplierProvince;
    private String supplierPostalCode;
    private String supplierEmail;
    private String supplierContact;
}
