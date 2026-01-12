package com.bhavuk.demo.rest;

import com.bhavuk.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    // define @PostContruct method to load student data ...... only once
    @PostConstruct
    public void loadData(){

        theStudents = new ArrayList<>();

        theStudents.add(new Student("hare","Krishna"));
        theStudents.add(new Student("Shree","Ram"));
        theStudents.add(new Student("Radhe","Krishna"));
    }

//    define endpoint for "/students" - returns a list of students

    @GetMapping("/students")
    public List<Student> getStudents(){

        return theStudents;
    }

    // define endpoint "/student/{studentId}" - return student at index

    @GetMapping("/student/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        //check studentId against list index size
        if((studentId >= theStudents.size()) || (studentId < 0)){
            throw new StudentNotFoundException("Student not found with id "+ studentId);
        }

        //return student for the index
        return theStudents.get(studentId);
    }
}
