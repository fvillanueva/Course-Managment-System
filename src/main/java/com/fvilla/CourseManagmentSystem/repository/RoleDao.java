package com.fvilla.CourseManagmentSystem.repository;

import com.fvilla.CourseManagmentSystem.entity.Role;

public interface RoleDao {

     Role findRoleByName(String theRoleName);

}
