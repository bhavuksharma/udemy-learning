package com.bhavuk.cruddemo.dao;

import com.bhavuk.cruddemo.entity.Course;
import com.bhavuk.cruddemo.entity.Instructor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDaoImpl implements AppDao{

    //define field for entity manager
    EntityManager entityManager;

    //inject the entity manager using constructor injection
    @Autowired
    public AppDaoImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {

        //retrive instructor based on id
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        //get the courses
        List<Course> courses = tempInstructor.getCourses();

        //break association of all the courses for the instructor
        for(Course tempCourse: courses){
            tempCourse.setInstructor(null);
        }

        //delete instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {

        //create query
        TypedQuery<Course> query = entityManager.createQuery(
                              "from Course where instructor.id= :data", Course.class);
        query.setParameter("data",theId);

        //execute query
        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {

        //create query
        TypedQuery<Instructor> query = entityManager.createQuery(
                                            "select i from Instructor i "
                                                   + " join fetch i.courses "
                                                   + "JOIN FETCH i.instructorDetail "
                                                   + "where i.id= :data" , Instructor.class);
        query.setParameter("data",theId);

        //execute query
        Instructor instructor = query.getSingleResult();

        return instructor;

    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void update(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {

        //retrieve the course
        Course tempCourse = entityManager.find(Course.class, theId);

        //delete the course
        entityManager.remove(tempCourse);
    }
}
