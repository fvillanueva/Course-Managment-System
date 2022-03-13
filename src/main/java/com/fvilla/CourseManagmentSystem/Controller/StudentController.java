package com.fvilla.CourseManagmentSystem.Controller;

import com.fvilla.CourseManagmentSystem.entity.User;
import com.fvilla.CourseManagmentSystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    private UserService userService;

    public StudentController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        User user = new User();
        theModel.addAttribute("user", user);
        return "student-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("student") User theUser) {
        userService.saveStudentRegistration(theUser);
        return "redirect:/";
    }

    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam("userId") int userId){
       userService.deleteUserById(userId);
        return "redirect:/";
    }

    @GetMapping("/deleteCourseFromUser")
    public String deleteCourseFromUser(@RequestParam("courseId") int courseId){

        userService.deleteCourseFromUser(courseId);

        return "redirect:/";
    }



}
