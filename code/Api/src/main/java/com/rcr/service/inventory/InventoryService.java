package com.rcr.service.inventory;

import com.rcr.domain.Inventory;
import org.springframework.stereotype.Service;

public interface InventoryService {

    void saveInventory(Inventory inventory);

    long getMaterialQty(long id);
}
