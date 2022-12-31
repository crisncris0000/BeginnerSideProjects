package com.springboot.ShoppingSite.controller;

import com.springboot.ShoppingSite.Entity.Category;
import com.springboot.ShoppingSite.Entity.Contact;
import com.springboot.ShoppingSite.Entity.Item;
import com.springboot.ShoppingSite.Service.CategoryService;
import com.springboot.ShoppingSite.Service.EmailSenderService;
import com.springboot.ShoppingSite.Service.ItemService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
@Controller
public class UserController {

    @Autowired
    ItemService itemService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    EmailSenderService senderService;

    @GetMapping("/home")
    public String index(Model model){
        model.addAttribute("clothingItems", itemService.findAll());

        return "index";
    }

    @GetMapping("/crafts")
    public String craftsPage(Model model){
        return "crafts";
    }

    @GetMapping("/crafts/new")
    public String createPost(Model model){

        Item item = new Item();

        Category clothing = categoryService.findCategoryById(1);
        Category cosmetics = categoryService.findCategoryById(2);
        Category celebration = categoryService.findCategoryById(3);
        Category other = categoryService.findCategoryById(4);

        model.addAttribute("item", item);

        model.addAttribute("clothing", clothing.getId());
        model.addAttribute("cosmetics", cosmetics.getId());
        model.addAttribute("celebration", celebration.getId());
        model.addAttribute("other", other.getId());

        return "add-form";
    }

    @PostMapping("/crafts")
    public String saveItem(@ModelAttribute("item") Item item, @RequestParam("fileImage")MultipartFile file) throws IOException{

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        item.setImage(fileName);

        Item savedItem = item;
        itemService.saveItem(item);

        String uploadDir = "./item-images/" + savedItem.getId();

        Path uploadPath = Paths.get(uploadDir);

        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            System.out.print(filePath.toFile().getAbsolutePath());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e){
            throw new IOException("Could not save uploaded file: " + fileName);
        }


        return "redirect:/home";
    }

    @GetMapping("/contact")
    public String contactPage(Model model){

        Contact contact = new Contact();

        model.addAttribute("contact", contact);

        return "contact";
    }

    @PostMapping("/contactMe")
    public String contactForm(@ModelAttribute("contact") Contact contact){

        senderService.sendEmail(contact.getEmail(), "christopherrivera134@gmail.com",
                "Sent from " + contact.getName(), contact.getBody());

        return "redirect:/home";
    }


}
