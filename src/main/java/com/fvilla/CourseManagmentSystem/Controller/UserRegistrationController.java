package com.fvilla.CourseManagmentSystem.Controller;

import com.fvilla.CourseManagmentSystem.entity.User;
import com.fvilla.CourseManagmentSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;

    @Autowired
    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showMyLoginPage(Model theModel) {

        theModel.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping
    public String registerStudentAccount(@ModelAttribute("user") User registrationDto){
        userService.saveStudentRegistration(registrationDto);
        return "redirect:/registration?success";
    }
}
