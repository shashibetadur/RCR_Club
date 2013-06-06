package com.rcr.service.inventory;

import com.rcr.domain.Inventory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface InventoryService {

    void saveInventory(Inventory inventory);

    long getMaterialQty(long id);

    List<Inventory> getCurrentStock();

    Long getQtyAtDate(long id, Date onDate);

}
