package com.springboot.ShoppingSite.controller;

import com.springboot.ShoppingSite.Entity.Contact;
import com.springboot.ShoppingSite.Service.EmailSenderService;
import com.springboot.ShoppingSite.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    ItemService itemService;

    @Autowired
    EmailSenderService senderService;

    @GetMapping(value = {"/home", "/"})
    public String index(Model model) {

        model.addAttribute("clothingItems", itemService.findNumberOfClothingItems(4));
        model.addAttribute("cosmeticItems", itemService.findNumberOfCosmeticItems(4));
        model.addAttribute("celebrationItems", itemService.findNumberOfCelebrationItems(4));
        model.addAttribute("otherItems", itemService.findNumberOfOtherItems(4));

        return "index";
    }

    @GetMapping("/crafts")
    public String craftsPage() {
        return "crafts";
    }

    @GetMapping("/contact")
    public String contactPage(Model model) {

        Contact contact = new Contact();

        model.addAttribute("contact", contact);

        return "contact";
    }

    @PostMapping("/contactMe")
    public String contactForm(@ModelAttribute("contact") Contact contact) {

        senderService.sendEmail(contact.getEmail(), "christopherrivera134@gmail.com",
                "Sent from " + contact.getName(), contact.getBody());

        return "redirect:/home";
    }


}
