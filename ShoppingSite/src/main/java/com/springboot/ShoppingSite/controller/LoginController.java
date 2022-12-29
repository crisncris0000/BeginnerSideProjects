package com.springboot.ShoppingSite.controller;

import com.springboot.ShoppingSite.Entity.User;
import com.springboot.ShoppingSite.Service.AuthorityService;
import com.springboot.ShoppingSite.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    private int workload = 12;
    @Autowired
    UserService userService;

    @Autowired
    AuthorityService authorityService;

    @GetMapping("/login")
    private String loginPage(){
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model){

        User user = new User();

        model.addAttribute("user", user);

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user){
        user.setAuthority(authorityService.findAuthorityById(1));

        String salt = BCrypt.gensalt(workload);
        String cryptPassword = BCrypt.hashpw(user.getPassword(), salt);
        user.setPassword(cryptPassword);

        userService.saveUser(user);
        return "redirect:/home";
    }


}
