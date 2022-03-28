package com.fvilla.CourseManagmentSystem.service;

import com.fvilla.CourseManagmentSystem.repository.CourseRepository;
import com.fvilla.CourseManagmentSystem.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Mock private CourseRepository courseRepository;
    @Mock private UserRepository userRepository;
    private UserService underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserService();
    }


    @Test
    void findAllStudents() {
    }

    @Test
    void findAllTeachers() {
    }

    @Test
    void findAllAdmins() {
    }

    @Test
    void save() {
    }

    @Test
    void saveStudentRegistration() {
    }

    @Test
    void saveAdminRegistration() {
    }

    @Test
    void saveTeacherRegistration() {
    }

    @Test
    void findByUserName() {
    }

    @Test
    void loadUserByUsername() {
    }

    @Test
    void findCoursesByUsername() {
    }

    @Test
    void addCourseToStudent() {
    }

    @Test
    void deleteCourseFromUser() {
    }

    @Test
    void addTeacherToCourse() {
    }

    @Test
    void deleteUserById() {
    }
}