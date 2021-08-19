package com.fvilla.CourseManagmentSystem;

import com.fvilla.CourseManagmentSystem.repository.CourseRepository;
import com.fvilla.CourseManagmentSystem.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CourseManagmentSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseManagmentSystemApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner (UserRepository userRepository, CourseRepository courseRepository){
		return args -> {



		};
	}

}
