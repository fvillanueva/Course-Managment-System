package com.fvilla.CourseManagmentSystem.Controller;

import com.fvilla.CourseManagmentSystem.entity.Course;
import com.fvilla.CourseManagmentSystem.entity.User;
import com.fvilla.CourseManagmentSystem.service.CourseService;
import com.fvilla.CourseManagmentSystem.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;

@Controller
public class HomeController {

    private UserService userService;
    private CourseService courseService;

    public HomeController(UserService userService, CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }

    @GetMapping("/")
    public String home(Model userCoursesModel, Model studentModel, Model courseModel, Model adminModel, Model teacherModel){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Set<Course> userCourses = userService.findCoursesByUsername(auth.getName());
        userCoursesModel.addAttribute("userCourses", userCourses);

        List<User> theStudents = userService.findAllStudents();
        studentModel.addAttribute("students", theStudents);

        List<Course> theCourses = courseService.findAll();
        courseModel.addAttribute("courses", theCourses);

        List<User> theAdmins = userService.findAllAdmins();
        adminModel.addAttribute("admins", theAdmins);

        List<User> theTeachers = userService.findAllTeachers();
        teacherModel.addAttribute("teachers", theTeachers);

        return "home";
    }

}
