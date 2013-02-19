package com.rcr.web.controller;

import com.rcr.domain.*;
import com.rcr.service.material.MaterialService;
import org.apache.commons.collections.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.collections.CollectionUtils.collect;

@Controller
@RequestMapping("/bar")
public class BarController {

    private MaterialService materialService;

    @Autowired
    public BarController(MaterialService materialService) {
        this.materialService = materialService;
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
        return new ModelAndView("bar/material/list", "materials", materialService.getAllMaterials());
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
    public List<String> materialTypes() {
        List<String> materialTypes = new ArrayList<String>();
        for (Material.Type materialType : Material.Type.values()) {
            materialTypes.add(materialType.toString());
        }
        return materialTypes;
    }
}
