package com.rcr.service.inventory;

import com.rcr.domain.Inventory;
import com.rcr.repository.inventory.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    InventoryRepository inventoryRepository;

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void saveInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    @Override
    public long getMaterialQty(long id) {
        long quantity = inventoryRepository.getMaterialQty(id);
        return quantity;
    }

    @Override
    public List<Inventory> getCurrentStock() {
        return inventoryRepository.getCurrentStock();
    }
}
