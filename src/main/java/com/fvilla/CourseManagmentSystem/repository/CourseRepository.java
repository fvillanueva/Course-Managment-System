package com.fvilla.CourseManagmentSystem.repository;

import com.fvilla.CourseManagmentSystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

     @Query("SELECT u FROM Course u ORDER BY u.name")
     List<Course> findAllByOrderByNameAsc();

}
