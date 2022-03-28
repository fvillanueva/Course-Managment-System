package com.fvilla.CourseManagmentSystem.service;

import com.fvilla.CourseManagmentSystem.entity.Course;
import com.fvilla.CourseManagmentSystem.repository.CourseRepository;
import com.fvilla.CourseManagmentSystem.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock private CourseRepository courseRepository;
    @Mock private UserRepository userRepository;
    private CourseService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CourseService(userRepository, courseRepository);
    }

    @Test
    void findAll() {
        //When
        underTest.findAll();
        //Then
        verify(courseRepository).findAllByOrderByNameAsc();

    }

    @Test
    void itShouldSave() {
        //given
        Course course = new Course("name", "description");

        //when
        underTest.save(course);

        //then
        ArgumentCaptor<Course> courseArgumentCaptor = ArgumentCaptor.forClass(Course.class);

        verify(courseRepository).save(courseArgumentCaptor.capture());

        Course capturedCourse = courseArgumentCaptor.getValue();

        assertThat(capturedCourse).isEqualTo(course);
    }

    @Test
    void itShouldFindById() {
       //given
        when(courseRepository.findById(1)).thenReturn(Optional.of(new Course(1, "name", "description")));

        //when
       Course found = underTest.findById(1);

       //then
       assertThat(found.getId()).isEqualTo(1);
       assertThat(found.getName()).isEqualTo("name");
       assertThat(found.getDescription()).isEqualTo("description");
    }

    @Test
    void itShouldDeleteCourseById() {
        //given
        when(courseRepository.findById(1)).thenReturn(Optional.of(new Course(1, "name", "description")));

         //when
        underTest.deleteCourseById(1);

        //then
        assertThat(underTest.findById(1).equals(null));
    }

}