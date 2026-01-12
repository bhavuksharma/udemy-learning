package com.bhavuk.cruddemo.dao;

import com.bhavuk.cruddemo.entity.Instructor;
import com.bhavuk.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

        //delete instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {

        //retrieve instructor detail based on id
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        //remove the associated object reference
        //break the bi-directional link
        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        //remove the instructor detail
        entityManager.remove(tempInstructorDetail);
    }
}
