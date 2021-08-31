package com.fvilla.CourseManagmentSystem.repository;

import com.fvilla.CourseManagmentSystem.entity.Role;

public interface RoleDao {

    public Role findRoleByName(String theRoleName);

}
