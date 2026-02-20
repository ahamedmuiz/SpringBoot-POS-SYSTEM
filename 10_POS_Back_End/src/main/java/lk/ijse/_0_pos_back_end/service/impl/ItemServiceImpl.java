package lk.ijse._0_pos_back_end.service.impl;

import lk.ijse._0_pos_back_end.dto.CustomerDTO;
import lk.ijse._0_pos_back_end.dto.ItemDTO;
import lk.ijse._0_pos_back_end.entity.Customer;
import lk.ijse._0_pos_back_end.entity.Item;
import lk.ijse._0_pos_back_end.repository.ItemRepository;
import lk.ijse._0_pos_back_end.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;


    @Override
    public void saveItem(ItemDTO itemDTO) {

        if (itemDTO == null) {
            throw new NullPointerException("Item DTO is null");
        }
        itemRepository.save(new Item(itemDTO.getiCode(),itemDTO.getiDescription(),itemDTO.getiPrice(),itemDTO.getiQty()));



    }

    @Override
    public void updateItem(ItemDTO itemDTO) {
        itemRepository.save(new Item(itemDTO.getiCode(),itemDTO.getiDescription(),itemDTO.getiPrice(),itemDTO.getiQty()));

    }

    @Override
    public void deleteItem(String id) {
        itemRepository.deleteById(id);

    }

    @Override
    public List<ItemDTO> getAllItems() {

        List<Item> items = itemRepository.findAll();
        List<ItemDTO> itemDTOList = new ArrayList<>();

        for(Item i:items){
            itemDTOList.add(new ItemDTO(i.getICode(),i.getIDescription(),i.getIPrice(),i.getIQty()));
        }
        return itemDTOList;
    }
}

