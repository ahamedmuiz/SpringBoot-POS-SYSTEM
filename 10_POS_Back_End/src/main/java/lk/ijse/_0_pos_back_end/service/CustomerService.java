package lk.ijse._0_pos_back_end.service;


import lk.ijse._0_pos_back_end.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    public void saveCustomer(CustomerDTO customerDTO);
    public void updateCustomer(CustomerDTO customerDTO);
    public void deleteCustomer(String id);

    List<CustomerDTO> getAllCustomers();
}