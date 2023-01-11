package com.springboot.ShoppingSite.controller;

import com.springboot.ShoppingSite.Entity.Contact;
import com.springboot.ShoppingSite.Entity.User;
import com.springboot.ShoppingSite.Service.EmailSenderService;
import com.springboot.ShoppingSite.Service.ItemService;
import com.springboot.ShoppingSite.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    ItemService itemService;

    @Autowired
    EmailSenderService senderService;

    @Autowired
    UserService userService;

    @GetMapping(value = {"/home", "/"})
    public String index(Model model) {

        model.addAttribute("clothingItems", itemService.findNumberOfClothingItems(4));
        model.addAttribute("cosmeticItems", itemService.findNumberOfCosmeticItems(4));
        model.addAttribute("celebrationItems", itemService.findNumberOfCelebrationItems(4));
        model.addAttribute("otherItems", itemService.findNumberOfOtherItems(4));

        return "index";
    }

    @GetMapping("/crafts")
    public String craftsPage(Model model) {

        model.addAttribute("allItems", itemService.findAll());

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

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.findUserByName(auth.getName());

        if(user != null) {
            senderService.sendEmail("christopherrivera134@gmail.com",
                    "Sent from " + contact.getName(), contact.getBody() + "\n\nReturn contact email to: " + user.getUsername());
        } else {
            senderService.sendEmail("christopherrivera134@gmail.com",
                    "Sent from " + contact.getName(), contact.getBody() + "\n\nReturn contact email to: " + contact.getEmail());
        }
        return "redirect:/home";
    }


}
