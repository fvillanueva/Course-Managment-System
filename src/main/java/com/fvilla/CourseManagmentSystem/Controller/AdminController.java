package com.fvilla.CourseManagmentSystem.Controller;

import com.fvilla.CourseManagmentSystem.entity.User;
import com.fvilla.CourseManagmentSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        User user = new User();

        theModel.addAttribute("admin", user);

        return "admin-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("admin") User theUser) {

        userService.saveAdminRegistration(theUser);

        return "redirect:/";
    }

    @GetMapping("/deleteAdmin")
    public String deleteAdmin(@RequestParam("userId") int userId){
        userService.deleteUserById(userId);
        return "redirect:/";
    }



}
