package com.fvilla.CourseManagmentSystem.Controller;

import com.fvilla.CourseManagmentSystem.entity.Content;
import com.fvilla.CourseManagmentSystem.entity.Course;
import com.fvilla.CourseManagmentSystem.service.CourseService;
import com.fvilla.CourseManagmentSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        userService.addCourseToStudent(theId);
        return "redirect:/";
    }

    @RequestMapping(value = "/addTeacher", method = {RequestMethod.GET, RequestMethod.PUT})
    public String addTeacher(int idCourse, int teacherId){
        userService.addTeacherToCourse(idCourse, teacherId);
        return "redirect:/";
    }

    @GetMapping("/{courseId}")
    public String showCoursePage(@PathVariable("courseId") int courseId, Model theModel){
        Course theCourse = courseService.findById(courseId);
        theModel.addAttribute("course", theCourse);
        return "course-page";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model courseModel) {
        Course theCourse = new Course();
        courseModel.addAttribute("course", theCourse);

        return "course-form";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute("course") Course theCourse) {
        courseService.save(theCourse);
        return "redirect:/";
    }

    @GetMapping("/deleteCourse")
    public String deleteCourse(@RequestParam("courseId") int courseId){
        courseService.deleteCourseById(courseId);
        return "redirect:/";
    }

    @GetMapping("/{id}/addVideo")
    public String addVideo(@PathVariable int id, Model videoModel){
        Content video = new Content();
        videoModel.addAttribute("video", video);
        return "video-form";
    }

    @PostMapping("/{id}/addVideo/save")
    public String saveVideo(@PathVariable int id, @ModelAttribute("video")Content video){
        System.out.println(video);
        courseService.addVideoToCourse(video, id);
        return "redirect:/course/"+id;
    }

    @PostMapping("/{id}/addComment")
    public String addComment(@PathVariable int id, @ModelAttribute("addedComment") String addedComment){
        courseService.addCommentToCourse(id, addedComment);
        return "redirect:/course/"+id;
    }

    @GetMapping("/{courseId}/deleteComment")
    public String deleteComment(@PathVariable int courseId, @RequestParam("commentId") int commentId){
        courseService.deleteCommentFromCourse(courseId, commentId);
        return "redirect:/course/"+courseId;
    }

    @GetMapping("/{courseId}/deleteReply")
    public String deleteReply(@PathVariable int courseId, @RequestParam("commentId") int commentId, @RequestParam("replyId") int replyId){
        courseService.deleteReplyFromComment(courseId, commentId, replyId);
        return "redirect:/course/"+courseId;
    }

    @PostMapping(value = "/{courseId}/addReply")
    public String addReply(@PathVariable int courseId, @RequestParam("commentId") int commentId, @ModelAttribute("reply") String reply){
        courseService.replyComment(courseId, commentId, reply);
        return "redirect:/course/"+courseId;
    }
}
