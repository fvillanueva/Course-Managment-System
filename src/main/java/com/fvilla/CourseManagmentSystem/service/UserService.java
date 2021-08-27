package com.fvilla.CourseManagmentSystem.service;

import com.fvilla.CourseManagmentSystem.entity.Role;
import com.fvilla.CourseManagmentSystem.entity.User;
import com.fvilla.CourseManagmentSystem.repository.RoleDao;
import com.fvilla.CourseManagmentSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    private RoleDao roleDao;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    public List<User> findAll() {
        return userRepository.findAllByOrderByLastNameAsc();
    }

    public void save(User theUser) {

        userRepository.save(theUser);
    }


    // REGISTRATION
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

}
