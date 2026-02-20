package lk.ijse._0_pos_back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private String itemCode;
    private int quantity;
    private double unitPrice;
}