package lk.ijse._0_pos_back_end.controller;

import lk.ijse._0_pos_back_end.dto.OrderDTO;
import lk.ijse._0_pos_back_end.service.OrderService;
import lk.ijse._0_pos_back_end.util.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:63342", methods = {RequestMethod.POST, RequestMethod.OPTIONS})
@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<APIResponse<String>> placeOrder(@RequestBody OrderDTO orderDTO) {
        orderService.placeOrder(orderDTO);
        return new ResponseEntity<>(
                new APIResponse<>(201, "Order Placed Successfully", null),
                HttpStatus.CREATED
        );
    }
}