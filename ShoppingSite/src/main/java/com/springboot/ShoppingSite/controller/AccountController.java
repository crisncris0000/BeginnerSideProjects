package com.springboot.ShoppingSite.controller;

import com.springboot.ShoppingSite.Entity.ConfirmationToken;
import com.springboot.ShoppingSite.Entity.User;
import com.springboot.ShoppingSite.Service.AuthorityService;
import com.springboot.ShoppingSite.Service.ConfirmationTokenService;
import com.springboot.ShoppingSite.Service.EmailSenderService;
import com.springboot.ShoppingSite.Service.Implementation.MyUserDetailsService;
import com.springboot.ShoppingSite.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AccountController{
    @Autowired
    UserService userService;

    @Autowired
    AuthorityService authorityService;

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    EmailSenderService emailService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    ConfirmationTokenService confirmationTokenService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {

        User user = new User();

        model.addAttribute("user", user);

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {

        if(userService.doesUserExist(user.getUsername())){
            model.addAttribute("errorMessage", "User already exists!");
            return "register";
        } else {

            user.setAuthority(authorityService.findAuthorityById(1));
            user.setEnabled(false);
            String cryptPassword = userDetailsService.cryptPassword(user.getPassword());
            user.setPassword(cryptPassword);
            userService.saveUser(user);

            String webUrl = "http://" + request.getServerName() + ":" +
                            request.getServerPort() + request.getContextPath();

            ConfirmationToken confirmationToken = new ConfirmationToken(user);

            emailService.sendEmail(user.getUsername(),
                    "Email verification",
                            "Please confirm your email using this link " +
                                    webUrl + "/confirm-account?token=" + confirmationToken.getConfirmationToken()
            );

            confirmationTokenService.saveToken(confirmationToken);

            model.addAttribute("email", user.getUsername());
        }

        return "redirect:/home";
    }

    @GetMapping("/confirm-account")
    public String confirmUserAccount(Model model, @RequestParam("token") String confirmationToken){
        ConfirmationToken token = confirmationTokenService.findConfirmationToken(confirmationToken);

        if(confirmationToken != null){
            User user = userService.findByUsername(token.getUser().getUsername());

            user.setEnabled(true);
            userService.saveUser(user);
        } else {
            return "error";
        }

        return "redirect:/home";
    }


}
