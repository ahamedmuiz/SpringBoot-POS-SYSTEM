package lk.ijse._0_pos_back_end.repository;

import lk.ijse._0_pos_back_end.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,String> {
}
