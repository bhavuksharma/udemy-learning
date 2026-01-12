package com.learncode.springboot.cruddemo.dao;

import com.learncode.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{

    // create filed for entityManger

    EntityManager entityManager;

    //setup constructor

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager){

        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {

        //create query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

        //execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        //return result
        return employees;
    }

    @Override
    public Employee findById(int theId) {

        // find the employee
        Employee theEmployee = entityManager.find(Employee.class,theId);

        //return the employee
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {

        //save the employee
        Employee dbEmployee = entityManager.merge(theEmployee);

        //return the dbEmployee which is returned from db after saving the object
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {

        //find the employee
        Employee theEmployee = entityManager.find(Employee.class,theId);

        //delete the employee
        entityManager.remove(theEmployee);
    }
}
