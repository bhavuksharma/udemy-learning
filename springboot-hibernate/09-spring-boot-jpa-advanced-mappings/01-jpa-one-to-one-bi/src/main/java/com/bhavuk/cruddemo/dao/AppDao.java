package com.bhavuk.cruddemo.dao;

import com.bhavuk.cruddemo.entity.Instructor;
import com.bhavuk.cruddemo.entity.InstructorDetail;

public interface AppDao {

    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);
}
