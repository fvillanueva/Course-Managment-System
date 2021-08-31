package com.fvilla.CourseManagmentSystem.Controller;

import com.fvilla.CourseManagmentSystem.entity.Course;
import com.fvilla.CourseManagmentSystem.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/course")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;

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
