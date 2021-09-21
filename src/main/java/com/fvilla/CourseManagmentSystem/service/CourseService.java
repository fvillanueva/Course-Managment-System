package com.fvilla.CourseManagmentSystem.service;

import com.fvilla.CourseManagmentSystem.entity.Course;
import com.fvilla.CourseManagmentSystem.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
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

        Course course = null;

        if(result.isPresent()){
            course = result.get();
        } else {
          throw new RuntimeException("Did not find the course id " + id);
        }

        return course;
    }

    public Optional<Course> getOne(Integer id) { return courseRepository.findById(id);
    }

    public void deleteCourseById(int courseId) {
        courseRepository.deleteById(courseId);
    }
}
