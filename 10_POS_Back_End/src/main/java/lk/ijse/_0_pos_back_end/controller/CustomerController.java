package lk.ijse._0_pos_back_end.controller;

import jakarta.validation.Valid;
import lk.ijse._0_pos_back_end.dto.CustomerDTO;
import lk.ijse._0_pos_back_end.service.CustomerService;
import lk.ijse._0_pos_back_end.util.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(
        origins = "http://localhost:63342",
        methods = {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.OPTIONS
        }
)

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<APIResponse<String>> saveCustomer(@RequestBody @Valid CustomerDTO customerDTO) {

        customerService.saveCustomer(customerDTO);

        return new ResponseEntity<>(new APIResponse<>

                (201,"Customer Saved", null),

                HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<APIResponse<String>> updateCustomer(@RequestBody @Valid
            CustomerDTO customerDTO) {

        customerService.updateCustomer(customerDTO);

        return new ResponseEntity<>(
                new APIResponse<>(201,"Customer Updated", null),
                HttpStatus.OK
        );

    }

    @DeleteMapping
    public ResponseEntity<APIResponse<String>> deleteCustomer(@RequestParam String id) {

        customerService.deleteCustomer(id);
        return new ResponseEntity<>(new APIResponse<>(200,"Customer Deleted", null),
                HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<APIResponse<List<CustomerDTO>>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(new APIResponse<>(200, "Success", customers), HttpStatus.OK);
    }
}
