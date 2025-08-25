package com.example.demo.Controller; 

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Validated @ModelAttribute("user") User user, 
                              BindingResult result, 
                              Model model) {
        
        // Check for validation errors first
        if (result.hasErrors()) {
            return "register";
        }

        // Check if passwords match
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("error", "Passwords do not match");
            return "register";
        }

        // Check if email already exists
        if (userService.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "Email already exists");
            return "register";
        }

        // Check if username already exists
        if (userService.existsByUsername(user.getName())) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }

        // Register the user
        try {
            userService.registerUser(user);
            return "redirect:/login?registered";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred during registration: " + e.getMessage());
            return "register";
        }
    }
}