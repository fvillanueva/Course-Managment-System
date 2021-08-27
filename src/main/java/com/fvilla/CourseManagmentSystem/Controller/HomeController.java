package com.fvilla.CourseManagmentSystem.Controller;

import com.fvilla.CourseManagmentSystem.entity.Course;
import com.fvilla.CourseManagmentSystem.entity.User;
import com.fvilla.CourseManagmentSystem.service.CourseService;
import com.fvilla.CourseManagmentSystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private UserService userService;
    private CourseService courseService;

    public HomeController(UserService userService, CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }

    @GetMapping("/")
    public String home(Model studentModel, Model courseModel){

        List<User> theStudents = userService.findAll();

        studentModel.addAttribute("students", theStudents);

        List<Course> theCourses = courseService.findAll();

        courseModel.addAttribute("courses", theCourses);

        return "home";
    }

}
