package com.fvilla.CourseManagmentSystem.repository;

import com.fvilla.CourseManagmentSystem.entity.User;


public interface UserDao {

     User findByUserName(String userName);

     void save(User user);

}
