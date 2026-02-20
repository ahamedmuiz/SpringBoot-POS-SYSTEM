package lk.ijse._0_pos_back_end.service;

import lk.ijse._0_pos_back_end.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    public void saveItem(ItemDTO itemDTO);
    public void updateItem(ItemDTO itemDTO);
    public void deleteItem(String id);

    List<ItemDTO> getAllItems();
}
