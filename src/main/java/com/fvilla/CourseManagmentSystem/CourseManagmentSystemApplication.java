package com.fvilla.CourseManagmentSystem;

import com.fvilla.CourseManagmentSystem.entity.Course;
import com.fvilla.CourseManagmentSystem.entity.Student;
import com.fvilla.CourseManagmentSystem.repository.CourseRepository;
import com.fvilla.CourseManagmentSystem.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;


@SpringBootApplication
public class CourseManagmentSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseManagmentSystemApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner (StudentRepository studentRepository, CourseRepository courseRepository){
		return args -> {



		};
	}

}
