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
@RequestMapping("/teacher")
public class TeacherController {


    private UserService userService;

    public TeacherController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        User user = new User();

        theModel.addAttribute("user", user);

        return "teacher-form";
    }

    @PostMapping("/save")
    public String saveTeacher(@ModelAttribute("teacher") User theUser) {

        userService.saveTeacherRegistration(theUser);

        return "redirect:/";
    }

}
