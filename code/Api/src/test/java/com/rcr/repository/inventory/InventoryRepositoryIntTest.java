package com.rcr.repository.inventory;

import com.rcr.domain.Inventory;
import com.rcr.domain.Material;
import com.rcr.repository.material.MaterialRepository;
import com.rcr.testcommon.BaseIntTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;


@Ignore
public class InventoryRepositoryIntTest extends BaseIntTest {

    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private MaterialRepository materialRepository;

    @Test

    public void shouldGetCurrentStock() {
       /* Material materialOne = new Material();
        materialOne.setName("nameOne");
        materialOne.setUnit("ml");
        materialOne.setPrice(100);
        materialOne.setMaterialType('L');
        materialOne.setDescription("desc");
        materialRepository.save(materialOne);

        Material materialTwo = new Material();
        materialTwo.setName("nameOne");
        materialTwo.setUnit("ml");
        materialTwo.setPrice(100);
        materialTwo.setMaterialType('L');
        materialTwo.setDescription("desc");
        materialRepository.save(materialTwo);

        Inventory inventoryOne = new Inventory();
        inventoryOne.setMaterial(materialOne);
        inventoryOne.setDate(new Date());
        inventoryOne.setEntryDate(new Date());
        inventoryOne.setCurrentStock(10);
        inventoryOne.setQuantity(10);
        inventoryOne.setCreditDebit('C');
        inventoryRepository.save(inventoryOne);

        Inventory inventoryTwo = new Inventory();
        inventoryTwo.setMaterial(materialOne);
        inventoryTwo.setDate(new Date());
        inventoryTwo.setEntryDate(new Date());
        inventoryTwo.setCurrentStock(20);
        inventoryTwo.setQuantity(10);
        inventoryTwo.setCreditDebit('C');
        inventoryRepository.save(inventoryTwo);

        Inventory inventoryThree = new Inventory();
        inventoryThree.setMaterial(materialTwo);
        inventoryThree.setDate(new Date());
        inventoryThree.setEntryDate(new Date());
        inventoryThree.setCurrentStock(5);
        inventoryThree.setQuantity(5);
        inventoryThree.setCreditDebit('C');
        inventoryRepository.save(inventoryThree);

        Inventory inventoryFour = new Inventory();
        inventoryFour.setMaterial(materialTwo);
        inventoryFour.setDate(new Date());
        inventoryFour.setEntryDate(new Date());
        inventoryFour.setCurrentStock(30);
        inventoryFour.setQuantity(25);
        inventoryFour.setCreditDebit('C');
        inventoryRepository.save(inventoryFour);*/
        List<Inventory> currentStock = inventoryRepository.getCurrentStock();
        System.out.println(currentStock.size());
    }
}
