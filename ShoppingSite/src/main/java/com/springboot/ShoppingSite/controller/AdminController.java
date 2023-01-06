package com.springboot.ShoppingSite.controller;

import com.springboot.ShoppingSite.Entity.Category;
import com.springboot.ShoppingSite.Entity.Item;
import com.springboot.ShoppingSite.Service.CategoryService;
import com.springboot.ShoppingSite.Service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    CategoryService categoryService;

    @Autowired
    ItemService itemService;

    @GetMapping("/crafts/add")
    public String createPost(Model model) {

        Item item = new Item();

        Map<String, Integer> categoryMap = new HashMap();

        List<Category> categories = categoryService.findALl();

        for (Category category : categories) {
            categoryMap.put(categoryService.findCategoryById(category.getId()).getCategory(), category.getId());
        }

        model.addAttribute("item", item);

        model.addAllAttributes(categoryMap);

        return "add-form";
    }

    @PostMapping("/crafts/save")
    public String saveItem(@ModelAttribute("item") Item item, @RequestParam("fileImage") MultipartFile file) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        item.setImage(fileName);

        Item savedItem = item;
        itemService.saveItem(item);

        String uploadDir = "./item-images/" + savedItem.getId();

        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Could not save uploaded file: " + fileName);
        }

        return "redirect:/home";
    }

}
