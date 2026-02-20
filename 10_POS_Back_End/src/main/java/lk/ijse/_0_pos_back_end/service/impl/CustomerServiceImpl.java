package lk.ijse._0_pos_back_end.service.impl;

import lk.ijse._0_pos_back_end.dto.CustomerDTO;
import lk.ijse._0_pos_back_end.entity.Customer;
import lk.ijse._0_pos_back_end.repository.CustomerRepository;
import lk.ijse._0_pos_back_end.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {

        if (customerDTO == null) {
            throw new NullPointerException("Customer DTO is null");
        }
        customerRepository.save(new Customer(customerDTO.getcId(),customerDTO.getcName(),customerDTO.getcAddress()));
    }


    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        customerRepository.save(new Customer(customerDTO.getcId(),customerDTO.getcName(),customerDTO.getcAddress()));

    }

    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);

    }


    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        for (Customer c : customers) {

            customerDTOList.add(new CustomerDTO(c.getCid(), c.getCName(), c.getCAddress()));
        }

        return customerDTOList;
    }

}