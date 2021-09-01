package com.fvilla.CourseManagmentSystem.Controller;

import com.fvilla.CourseManagmentSystem.entity.Course;
import com.fvilla.CourseManagmentSystem.entity.User;
import com.fvilla.CourseManagmentSystem.service.CourseService;
import com.fvilla.CourseManagmentSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    public CourseController(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @GetMapping("/buyCourse")
    public String buyCourse(@RequestParam("courseId") int theId){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        userService.addCourseToStudent(theId, auth.getName());

        return "redirect:/";
    }

    @GetMapping("/page")
    public String showCoursePage(@RequestParam("courseId") int theId, Model theModel){

        Course theCourse = courseService.findById(theId);

        theModel.addAttribute("course", theCourse);

        return "course-page";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        Course theCourse = new Course();

        theModel.addAttribute("course", theCourse);

        return "course-form";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute("course") Course theCourse) {

        courseService.save(theCourse);

        return "redirect:/";
    }
}
