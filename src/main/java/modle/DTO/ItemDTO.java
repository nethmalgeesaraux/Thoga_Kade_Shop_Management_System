package modle.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ItemDTO {
    private String itemCode;
    private String description;
    private String category;
    private int qtyOnHand;
    private double unitPrice;


}
