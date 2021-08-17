package com.fvilla.CourseManagmentSystem.Controller;

import com.fvilla.CourseManagmentSystem.entity.Course;
import com.fvilla.CourseManagmentSystem.entity.Student;
import com.fvilla.CourseManagmentSystem.service.CourseService;
import com.fvilla.CourseManagmentSystem.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private StudentService studentService;
    private CourseService courseService;

    public HomeController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping("/")
    public String home(Model studentModel, Model courseModel){

        List<Student> theStudents = studentService.findAll();

        studentModel.addAttribute("students", theStudents);

        List<Course> theCourses = courseService.findAll();

        courseModel.addAttribute("courses", theCourses);

        return "home";
    }


}
