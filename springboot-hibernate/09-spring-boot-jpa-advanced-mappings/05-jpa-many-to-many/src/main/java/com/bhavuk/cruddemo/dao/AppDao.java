package com.bhavuk.cruddemo.dao;

import com.bhavuk.cruddemo.entity.Course;
import com.bhavuk.cruddemo.entity.Instructor;
import com.bhavuk.cruddemo.entity.Student;

import java.util.List;

public interface AppDao {

    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    List<Course> findCoursesByInstructorId(int theId);
    Instructor findInstructorByIdJoinFetch(int theId);
    void update(Instructor tempInstructor);
    void update(Course tempCourse);
    Course findCourseById(int  theId);
    void deleteCourseById(int theId);
    void save(Course theCourse);
    Course findCourseAndReviewsByCourseId(int theId);
    Course findCourseAndStudentsByCourseId(int theId);
    Student findStudentAndCoursesByStudentId(int theId);
    void update(Student tempStudent);
    void deleteStudentById(int theId);


}
