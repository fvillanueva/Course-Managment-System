package com.fvilla.CourseManagmentSystem.repository;

import com.fvilla.CourseManagmentSystem.entity.User;


public interface UserDao {

    public User findByUserName(String userName);

    public void save(User user);

}
