package com.springboot.ShoppingSite.controller;

import com.springboot.ShoppingSite.Entity.Contact;
import com.springboot.ShoppingSite.Service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    EmailSenderService senderService;

    @GetMapping("/home")
    public String index(){
        return "index";
    }

    @GetMapping("/crafts")
    public String craftsPage(){
        return "crafts";
    }

    @GetMapping("/contact")
    public String contactPage(Model model){

        Contact contact = new Contact();

        model.addAttribute("contact", contact);

        return "contact";
    }

    @PostMapping("/contactMe")
    public String contactForm(@ModelAttribute("contact") Contact contact){

        senderService.sendEmail(contact.getEmail(), "christopherrivera384@gmail.com",
                "Sent from " + contact.getName(), contact.getBody());

        return "redirect:/home";
    }

}
