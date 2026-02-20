package lk.ijse._0_pos_back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String orderId;
    private LocalDate orderDate;
    private String customerId;
    private List<OrderDetailDTO> orderDetails; // List of items in the order
}