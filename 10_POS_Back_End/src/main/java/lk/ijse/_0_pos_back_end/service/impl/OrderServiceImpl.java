package lk.ijse._0_pos_back_end.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse._0_pos_back_end.dto.OrderDTO;
import lk.ijse._0_pos_back_end.dto.OrderDetailDTO;
import lk.ijse._0_pos_back_end.entity.Customer;
import lk.ijse._0_pos_back_end.entity.Item;
import lk.ijse._0_pos_back_end.entity.OrderDetail;
import lk.ijse._0_pos_back_end.entity.Orders;
import lk.ijse._0_pos_back_end.exception.CustomException;
import lk.ijse._0_pos_back_end.repository.CustomerRepository;
import lk.ijse._0_pos_back_end.repository.ItemRepository;
import lk.ijse._0_pos_back_end.repository.OrderDetailRepository;
import lk.ijse._0_pos_back_end.repository.OrderRepository;
import lk.ijse._0_pos_back_end.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void placeOrder(OrderDTO orderDTO) {

        if (orderRepository.existsById(orderDTO.getOrderId())) {
            throw new CustomException("Order ID " + orderDTO.getOrderId() + " already exists!");
        }


        Customer customer = customerRepository.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new CustomException("Customer not found!"));


        Orders order = new Orders(orderDTO.getOrderId(), orderDTO.getOrderDate(), customer);
        orderRepository.save(order);


        for (OrderDetailDTO detailDTO : orderDTO.getOrderDetails()) {
            Item item = itemRepository.findById(detailDTO.getItemCode())
                    .orElseThrow(() -> new CustomException("Item " + detailDTO.getItemCode() + " not found!"));


            if (item.getIQty() < detailDTO.getQuantity()) {
                throw new CustomException("Insufficient quantity for Item: " + item.getICode());
            }

            item.setIQty(item.getIQty() - detailDTO.getQuantity());
            itemRepository.save(item);


            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrders(order);
            orderDetail.setItem(item);
            orderDetail.setQuantity(detailDTO.getQuantity());
            orderDetail.setUnitPrice(detailDTO.getUnitPrice());

            orderDetailRepository.save(orderDetail);
        }
    }
}