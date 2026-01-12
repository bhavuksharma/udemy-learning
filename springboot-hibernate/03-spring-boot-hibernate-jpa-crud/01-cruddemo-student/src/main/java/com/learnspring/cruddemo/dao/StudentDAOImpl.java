package com.learnspring.cruddemo.dao;

import com.learnspring.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // inject the entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    // implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {

        //create jql query for students
        TypedQuery<Student> theQuery = entityManager.createQuery("from Student order by lastName ", Student.class);

        //return students list
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {

        //create jpql query for students
        TypedQuery<Student> theQuery = entityManager.createQuery("from Student where lastName=:theData", Student.class);

        //set query parameters
        theQuery.setParameter("theData", theLastName);

        //return query results
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {

        //retrieve the student
        Student theStudent = entityManager.find(Student.class, id);

        //delete the
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int theQuery = entityManager.createQuery("DELETE FROM Student").executeUpdate();

        return theQuery;
    }
}
