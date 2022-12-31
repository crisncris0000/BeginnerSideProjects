package com.springboot.ShoppingSite.controller;

import com.springboot.ShoppingSite.Entity.Category;
import com.springboot.ShoppingSite.Entity.Contact;
import com.springboot.ShoppingSite.Entity.Item;
import com.springboot.ShoppingSite.Service.CategoryService;
import com.springboot.ShoppingSite.Service.EmailSenderService;
import com.springboot.ShoppingSite.Service.ItemService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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

    @GetMapping("/display/image/{id}")
    public void displayItemImage(@PathVariable int id, HttpServletResponse response) throws IOException{

        response.setContentType("image/png");

        Item item = itemService.findItemById(id);

        InputStream is = new ByteArrayInputStream(item.getImage());
        IOUtils.copy(is, response.getOutputStream());

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ID OF IMAGE " + item.getId());
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
    public String saveItem(@ModelAttribute("item") Item item){


        itemService.saveItem(item);
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
