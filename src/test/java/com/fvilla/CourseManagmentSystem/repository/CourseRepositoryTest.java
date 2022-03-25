package com.fvilla.CourseManagmentSystem.repository;

import com.fvilla.CourseManagmentSystem.entity.Course;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CourseRepositoryTest {

    @Autowired
    CourseRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindAllCoursesByOrderAsc(){
        //given
        Course course3 = new Course("course3", "description3", null, null, null);
        Course course1 = new Course("course1", "description1", null, null, null);
        Course course2 = new Course("course2", "description2", null, null, null);
        underTest.save(course1);
        underTest.save(course2);
        underTest.save(course3);
        //when
        List<Course> courses = underTest.findAllByOrderByNameAsc();
        boolean expected = courses.get(0).equals(course1) && courses.get(1).equals(course2) && courses.get(2).equals(course3);
        //then
        assertThat(expected).isTrue();
    }
}