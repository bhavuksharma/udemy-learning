package com.learncode.springboot.cruddemo.service;

import com.learncode.springboot.cruddemo.dao.EmployeeRepository;
import com.learncode.springboot.cruddemo.entity.Employee;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository){
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);

        Employee theEmployee = null;

        if(result.isPresent()){
            theEmployee = result.get();
        }
        else{
            throw new RuntimeException("Did not find the employee of id - " + theId);
        }

        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }


    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }
}
