package com.fvilla.CourseManagmentSystem.service;

import com.fvilla.CourseManagmentSystem.entity.Course;
import com.fvilla.CourseManagmentSystem.entity.Role;
import com.fvilla.CourseManagmentSystem.entity.User;
import com.fvilla.CourseManagmentSystem.repository.CourseRepository;
import com.fvilla.CourseManagmentSystem.repository.RoleDao;
import com.fvilla.CourseManagmentSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllStudents(){
        List<User> students = new ArrayList<>();
        Role role = roleDao.findRoleByName("ROLE_STUDENT");

        for (User user : userRepository.findAll()){

            if(user.getRoles().contains(role) ){
                students.add(user);
            }
        }

        return students;
    }

    public List<User> findAllTeachers() {
        List<User> teachers = new ArrayList<>();
        Role role = roleDao.findRoleByName("ROLE_TEACHER");

        for (User user : userRepository.findAll()){

            if(user.getRoles().contains(role) ){
                teachers.add(user);
            }
        }

        return teachers;
    }

    public List<User> findAllAdmins() {
        List<User> admins = new ArrayList<>();
        Role role = roleDao.findRoleByName("ROLE_ADMIN");

        for (User user : userRepository.findAll()){

            if(user.getRoles().contains(role) ){
                admins.add(user);
            }
        }

        return admins;
    }

    public void save(User theUser) {

        userRepository.save(theUser);
    }

    @Transactional
    public User saveStudentRegistration (User userRegistration){

        User user = new User();

        user.setUsername(userRegistration.getUsername());
        user.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
        user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_STUDENT")));
        user.setFirstName(userRegistration.getFirstName());
        user.setLastName(userRegistration.getLastName());
        user.setGender(userRegistration.getGender());
        user.setEmail(userRegistration.getEmail());
        user.setDateOfBirth(userRegistration.getDateOfBirth());
        user.setCountryOfBirth(userRegistration.getCountryOfBirth());

        return userRepository.save(user);
    }

    @Transactional
    public User saveAdminRegistration(User theUser) {

        User user = new User();

        user.setUsername(theUser.getUsername());
        user.setPassword(passwordEncoder.encode(theUser.getPassword()));
        user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_ADMIN")));
        user.setFirstName(theUser.getFirstName());
        user.setLastName(theUser.getLastName());
        user.setGender(theUser.getGender());
        user.setEmail(theUser.getEmail());
        user.setDateOfBirth(theUser.getDateOfBirth());
        user.setCountryOfBirth(theUser.getCountryOfBirth());

        return userRepository.save(user);
    }

    @Transactional
    public User saveTeacherRegistration(User theUser) {

        User user = new User();

        user.setUsername(theUser.getUsername());
        user.setPassword(passwordEncoder.encode(theUser.getPassword()));
        user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_TEACHER")));
        user.setFirstName(theUser.getFirstName());
        user.setLastName(theUser.getLastName());
        user.setGender(theUser.getGender());
        user.setEmail(theUser.getEmail());
        user.setDateOfBirth(theUser.getDateOfBirth());
        user.setCountryOfBirth(theUser.getCountryOfBirth());

        return userRepository.save(user);
    }

    public User findByUserName(String username) {

        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if(user == null)
            throw new UsernameNotFoundException("Invalid username or password.");

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public Set<Course> findCoursesByUsername(String username) {

        User student = findByUserName(username);
        Set<Course> courses = student.getCourses();

        return courses;
    }

    @Transactional
    public User addCourseToStudent (int courseId, String userUsername){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.findByUserName(auth.getName());
        user.addCourses(courseRepository.getById(courseId));

        return user;
    }
}
