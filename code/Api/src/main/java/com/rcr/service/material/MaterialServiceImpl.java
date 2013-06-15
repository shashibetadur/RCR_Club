package com.rcr.service.material;

import com.rcr.domain.Item;
import com.rcr.domain.Material;
import com.rcr.repository.material.ItemRepository;
import com.rcr.repository.material.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isEmpty;

@Service
public class MaterialServiceImpl implements MaterialService {

    private MaterialRepository materialRepository;
    private ItemRepository itemRepository;

    @Autowired
    public MaterialServiceImpl(MaterialRepository materialRepository, ItemRepository itemRepository) {
        this.materialRepository = materialRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public void saveMaterialDetails(Material material) {
        materialRepository.save(material);
    }

    @Override
    public Material getMaterialDetails(long materialId) {
        return materialRepository.get(materialId);
    }

    @Override
    public List<Material> getAllMaterials() {
        return materialRepository.getAll();
    }

    @Override
    public List<Material> getAllActiveMaterials() {
        return materialRepository.getAllActive();
    }

    @Override
    public void saveItemDetails(Item item) {
        itemRepository.save(item);
    }

    @Override
    public Item getItemDetails(long itemId) {
        return itemRepository.get(itemId);
    }

    public List<Item> getAllItems() {
        return itemRepository.getAll();
    }

    @Override
    public List<Material> searchMaterials(String searchToken) {

        if (isEmpty(searchToken) || isEmpty(searchToken.trim())) return new ArrayList<Material>();
        String[] searchTokens = searchToken.split("\\s+");
        return materialRepository.searchMaterials(Arrays.asList(searchTokens));
    }

    @Override
    public List<Item> searchItems(String searchToken) {
        if (isEmpty(searchToken) || isEmpty(searchToken.trim())) return new ArrayList<Item>();
        String[] searchTokens = searchToken.split("\\s+");
        return itemRepository.searchItems(Arrays.asList(searchTokens));
    }

    @Override
    public List<Item> getAllActiveItems() {
        return itemRepository.getAllActive();
    }
}
