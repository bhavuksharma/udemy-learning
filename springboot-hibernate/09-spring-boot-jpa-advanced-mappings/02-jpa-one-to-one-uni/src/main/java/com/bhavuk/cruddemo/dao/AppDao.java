package com.bhavuk.cruddemo.dao;

import com.bhavuk.cruddemo.entity.Instructor;

public interface AppDao {

    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
}
