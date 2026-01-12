package com.learncode.springboot.cruddemo.rest;

import com.learncode.springboot.cruddemo.dao.EmployeeDAO;
import com.learncode.springboot.cruddemo.entity.Employee;
import com.learncode.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {


    EmployeeService employeeService;

    //quick and dirty: inject employee dao (use constructor injection)
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    //expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    //add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){

        Employee theEmployee = employeeService.findById(employeeId);

        if (theEmployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId );
        }

        return theEmployee;
    }

    //add mapping for POST /employees - add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){

        //also just in case they pass id in json ..... set id to 0
        //this is to force a save of new item ..... instead of update

        theEmployee.setId(0);

        Employee dbEmployee  = employeeService.save(theEmployee);

        return dbEmployee;
    }

    //add mapping for PUT /employees - update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployeeById(@PathVariable int employeeId){

        Employee theEmployee = employeeService.findById(employeeId);

        //throw exception if null
        if(theEmployee == null){
            throw new RuntimeException("Employee Id not found - " + employeeId );
        }

        employeeService.deleteById(employeeId);

        return "deleted employee with Id - " + employeeId;
    }

}
