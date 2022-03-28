package com.fvilla.CourseManagmentSystem.service;

import com.fvilla.CourseManagmentSystem.entity.Comment;
import com.fvilla.CourseManagmentSystem.entity.Content;
import com.fvilla.CourseManagmentSystem.entity.Course;
import com.fvilla.CourseManagmentSystem.repository.CourseRepository;
import com.fvilla.CourseManagmentSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private UserRepository userRepository;

    private CourseRepository courseRepository;

    @Autowired
    public CourseService(UserRepository userRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAllByOrderByNameAsc();
    }

    public void save(Course theCourse) {
        courseRepository.save(theCourse);
    }

    public Course findById(int id){
        Optional<Course> result = courseRepository.findById(id);

        Course course;

        if(result.isPresent()){
            course = result.get();
        } else {
          throw new RuntimeException("Did not find the course id " + id);
        }

        return course;
    }

    public void deleteCourseById(int courseId) {
        courseRepository.deleteById(courseId);
    }

    public void addCommentToCourse(int courseId, String comment) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Comment auxComment = new Comment(
                LocalDate.now(),
                userRepository.findByUsername(username).getFirstName() + " " + userRepository.findByUsername(username).getLastName(),
                comment
        );
        Course auxCourse = courseRepository.findById(courseId).orElseThrow();
        auxCourse.getComments().add(auxComment);
        courseRepository.save(auxCourse);
    }

    public void deleteCommentFromCourse(int courseId, int commentId) {
        Optional<Course> course = courseRepository.findById(courseId);
        Comment comment = course.get().getComments().stream().filter(n -> n.getId() == commentId).findAny().get();
        course.get().getComments().remove(comment);
        courseRepository.save(course.get());
    }

    public void replyComment(int courseId, int commentId, String reply) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Comment auxComment = new Comment(
                LocalDate.now(),
                userRepository.findByUsername(username).getFirstName() + " " + userRepository.findByUsername(username).getLastName(),
                reply
        );
        Optional<Course> course = courseRepository.findById(courseId);
        Comment comment = course.get().getComments().stream().filter(n -> n.getId() == commentId).findAny().get();
        comment.getChildren().add(auxComment);
    }

    public void deleteReplyFromComment(int courseId, int commentId, int replyId) {
        Optional<Course> course = courseRepository.findById(courseId);
        Comment comment = course.get().getComments().stream().filter(n -> n.getId() == commentId).findAny().get();
        Comment reply = comment.getChildren().stream().filter(c -> c.getId() == replyId).findAny().get();
        comment.getChildren().remove(reply);
    }

    public void addVideoToCourse(Content video, int id) {
        Course course = courseRepository.findById(id).get();
        List<Content> videos = course.getVideos();
        Content videoAux = new Content(video.getName(),video.getVideoURL(), video.getVideoExplanation());
        videos.add(videoAux);
        courseRepository.save(course);
    }
}
