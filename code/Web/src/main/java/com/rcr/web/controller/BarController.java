package com.rcr.web.controller;

import com.rcr.domain.*;
import com.rcr.service.inventory.InventoryService;
import com.rcr.service.material.MaterialService;
import org.apache.commons.collections.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

import static org.apache.commons.collections.CollectionUtils.collect;

@Controller
@RequestMapping("/bar")
public class BarController {

    private MaterialService materialService;
    private InventoryService inventoryService;

    @Autowired
    public BarController(MaterialService materialService, InventoryService inventoryService) {
        this.materialService = materialService;
        this.inventoryService = inventoryService;
    }

    @RequestMapping(value = "/material/createForm", method = RequestMethod.GET)
    public ModelAndView materialCreationForm() {
        return new ModelAndView("bar/material/createForm", "material", new Material());
    }

    @RequestMapping(value = "/material/save", method = RequestMethod.POST)
    public String saveMaterial(Material material, Model model) {
        model.asMap().clear();
        materialService.saveMaterialDetails(material);
        return "redirect:/bar/material/viewForm/" + material.getId();
    }

    @RequestMapping(value = "/material/viewForm/{id}", method = RequestMethod.GET)
    public ModelAndView materialViewForm(@PathVariable("id") long materialId) {
        return new ModelAndView("bar/material/viewForm", "material", materialService.getMaterialDetails(materialId));
    }

    @RequestMapping(value = "/material/editForm/{id}", method = RequestMethod.GET)
    public ModelAndView editMenuItem(@PathVariable("id") long materialId) {
        return new ModelAndView("bar/material/createForm", "material", materialService.getMaterialDetails(materialId));
    }

    @RequestMapping(value = "/material/list", method = RequestMethod.GET)
    public ModelAndView listMaterials() {
        List<Material> materialList = materialService.getAllMaterials();
        for (Material material : materialList) {
            material.setValue(material.getName());
        }
        return new ModelAndView("bar/material/list", "materials", materialList);
    }

    @RequestMapping(value = "/material/convertMaterials", method = RequestMethod.GET)
    public ModelAndView convertMaterials() {
        return new ModelAndView("bar/material/convertMaterials", "materials", materialService.getAllMaterials());
    }

    @RequestMapping(value = "/material/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    List<Material> searchMaterials(@RequestParam("searchToken") String searchToken) {

        List<Material> materials = materialService.searchMaterials(searchToken);

        if (materials.isEmpty()) return Collections.EMPTY_LIST;

        return materials;
    }

    @RequestMapping(value = "/material/searchStock", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    List<Material> searchStock(@RequestParam("searchToken") String searchToken, @RequestParam(value = "onDate", required = true) Date onDate) {

        List<Material> materials = materialService.searchMaterials(searchToken);

        if (materials.isEmpty()) return Collections.EMPTY_LIST;

        for(Material material: materials ) {
            material.setStock(inventoryService.getQtyAtDate(material.getId(),onDate));
        }
        return materials;
    }

    @RequestMapping(value = "/material/searchUpdateStock", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    List<Material> searchMaterialsUpdateStock(@RequestParam("searchToken") String searchToken) {

        List<Material> materials = materialService.searchMaterials(searchToken);

        if (materials.isEmpty()) return Collections.EMPTY_LIST;

        for(Material material: materials ) {
            material.setStock(inventoryService.getMaterialQty(material.getId()));
        }
        return materials;
    }

    @RequestMapping(value = "/item/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    List<Item> searchItems(@RequestParam("searchToken") String searchToken) {

        List<Item> items = materialService.searchItems(searchToken);

        if (items.isEmpty()) return Collections.EMPTY_LIST;

        return items;
    }

    @RequestMapping(value = "/item/createForm", method = RequestMethod.GET)
    public ModelAndView itemCreationForm() {
        return new ModelAndView("bar/item/createForm", "item", new Item());
    }

    @RequestMapping(value = "/item/save", method = RequestMethod.POST)
    public String saveItem(Item item, Model model) {
        model.asMap().clear();
        materialService.saveItemDetails(item);
        return "redirect:/bar/item/viewForm/" + item.getId();
    }

    @RequestMapping(value = "/item/viewForm/{id}", method = RequestMethod.GET)
    public ModelAndView itemViewForm(@PathVariable("id") long itemId) {
        return new ModelAndView("bar/item/viewForm", "item", materialService.getItemDetails(itemId));
    }

    @RequestMapping(value = "/item/editForm/{id}", method = RequestMethod.GET)
    public ModelAndView editItem(@PathVariable("id") long itemId) {
        return new ModelAndView("bar/item/createForm", "item", materialService.getItemDetails(itemId));
    }

    @RequestMapping(value = "/item/list", method = RequestMethod.GET)
    public ModelAndView listItems() {
        return new ModelAndView("bar/item/list", "items", materialService.getAllItems());
    }

    @RequestMapping(value = "/material/convertToItem/{id}", method = RequestMethod.GET)
    public ModelAndView convertToItem(@PathVariable("id") long materialId) {
        Material material = materialService.getMaterialDetails(materialId);
        Item item = new Item();
        item.setName(material.getName());
        item.setDescription(material.getDescription());
        item.setPrice(material.getPrice());
        return new ModelAndView("bar/item/createForm", "item", item);
    }

    @ModelAttribute("materialTypes")
    public Map<Character, String> materialTypes() {
        Map<Character, String> materialTypes = new HashMap<Character, String>();
        for (MaterialType materialType : MaterialType.values()) {
            materialTypes.put(materialType.getCode(), materialType.toString());
        }
        return materialTypes;
    }

    @ModelAttribute("itemTypes")
    public Map<Character, String> itemTypes() {
        Map<Character, String> itemTypes = new HashMap<Character, String>();
        for (ItemType itemType : ItemType.values()) {
            itemTypes.put(itemType.getCode(), itemType.toString());
        }
        return itemTypes;
    }
}
