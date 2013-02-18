package com.rcr.service.material;

import com.rcr.domain.Item;
import com.rcr.domain.Material;

import java.util.List;

public interface MaterialService {

    void saveMaterialDetails(Material material);

    Material getMaterialDetails(long materialId);

    List<Material> getAllMaterials();

    void saveItemDetails(Item item);

    Item getItemDetails(long itemId);

    List<Item> getAllItems();

    List<Material> searchMaterials(String searchToken);
}
