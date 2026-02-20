package lk.ijse._0_pos_back_end.controller;


import jakarta.validation.Valid;
import lk.ijse._0_pos_back_end.dto.CustomerDTO;
import lk.ijse._0_pos_back_end.dto.ItemDTO;
import lk.ijse._0_pos_back_end.service.CustomerService;
import lk.ijse._0_pos_back_end.service.ItemService;
import lk.ijse._0_pos_back_end.util.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:63342", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("api/v1/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<APIResponse<String>> saveItem(@RequestBody @Valid ItemDTO itemDTO) {


        itemService.saveItem(itemDTO);

        return new ResponseEntity<>(new APIResponse<>(
                201,"Item Saved", null),
                HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<APIResponse<String>> updateItem(@RequestBody @Valid ItemDTO itemDTO) {


        itemService.updateItem(itemDTO);

        return new ResponseEntity<>(new APIResponse<>(201,"Item Updated", null),
                HttpStatus.OK);


    }
    @DeleteMapping
    public ResponseEntity<APIResponse<String>> deleteItem(@RequestParam String id) {

        itemService.deleteItem(id);

        return new ResponseEntity<>(new APIResponse<>(200,"Item Deleted", null),
                HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<APIResponse<List<ItemDTO>>> getAllItems() {
        List<ItemDTO> items = itemService.getAllItems();
        return new ResponseEntity<>(new APIResponse<>(200, "Success", items), HttpStatus.OK);
    }
}
