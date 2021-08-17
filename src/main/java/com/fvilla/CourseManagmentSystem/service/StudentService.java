package com.fvilla.CourseManagmentSystem.service;

import com.fvilla.CourseManagmentSystem.entity.Student;
import com.fvilla.CourseManagmentSystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> findAll() {
        return studentRepository.findAllByOrderByLastNameAsc();
    }
}
