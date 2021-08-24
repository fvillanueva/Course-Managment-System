package com.fvilla.CourseManagmentSystem.Controller;

import com.fvilla.CourseManagmentSystem.entity.User;
import com.fvilla.CourseManagmentSystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students")
public class StudentController {

    private UserService userService;

    public StudentController(UserService userService) {
        this.userService = userService;

    }


    @PostMapping("/save")
    public String saveUser(@ModelAttribute("student") User theUser) {

        userService.save(theUser);

        return "redirect:/";
    }




}
