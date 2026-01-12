package com.learnspring.cruddemo;

import com.learnspring.cruddemo.dao.StudentDAO;
import com.learnspring.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
//            createStudent(studentDAO);
            createMultipleStudent(studentDAO);
//            readStudent(studentDAO);
//            queryForStudent(studentDAO);
//            queryForStudentByLastName(studentDAO);
//            updateStudent(studentDAO);
//            deleteStudent(studentDAO);
//            deleteAllStudent(studentDAO);
        };
    }

    private void deleteAllStudent(StudentDAO studentDAO) {

        System.out.println("deleteing all students");
        int deleteCount = studentDAO.deleteAll();
        System.out.println(deleteCount + " records deleted");
    }

    private void deleteStudent(StudentDAO studentDAO) {
        int studentId = 4;
        System.out.println("deleting student with id: " + studentId);
        studentDAO.delete(studentId);
    }

    private void updateStudent(StudentDAO studentDAO) {

        //retieve stuent based on id: primary key
        int id=1;
        System.out.println("geting student based on id: " + id);
        Student theStudent = studentDAO.findById(id);

        //change first name
        System.out.println("setting the student first name");
        theStudent.setFirstName("Parth");

        //update the student
        System.out.println("hey updating the student");
        studentDAO.update(theStudent);

        //display the updated student
        System.out.println("updated student: "+theStudent);
    }

    private void queryForStudentByLastName(StudentDAO studentDAO) {
        //get list of students by last name
        List<Student> students = studentDAO.findByLastName("krishna");

        //display the students
        for(Student student: students){
            System.out.println(student);
        }
    }

    private void queryForStudent(StudentDAO studentDAO) {
        // retieve Student list
        List<Student> studentsList = studentDAO.findAll();

        //print students
        for(Student tempStudent: studentsList) {
            System.out.println(tempStudent);
        }

    }

    private void readStudent(StudentDAO studentDAO) {

        // create student object
        System.out.println("hey creating a new student");
            Student tempStudent = new Student("Halwai","Mahesh","mahesh@123.com");

        // save student object
        System.out.println("hey saving the student");
        studentDAO.save(tempStudent);

        // display id of saved student
        System.out.println("hey displaying the student id: "+tempStudent.getId());

        // retrieve student based on the id of the student: primary key
        System.out.println("hey retrieving the student");
        Student foundStudent = studentDAO.findById(tempStudent.getId());

        // display student
        System.out.println("hey displaying the student");
        System.out.println(foundStudent);
    }

    private void createMultipleStudent(StudentDAO studentDAO) {

        //create 3 student object
        System.out.println("hey creating 3 student objects");
        Student tempStudent1 = new Student("Om","Puri","puri@01.com");
        Student tempStudent2 = new Student("Radhe","Krishna","krishna@02.com");
        Student tempStudent3 = new Student("Radha","Raman","raman@03.com");

        //save student objects
        System.out.println("hey saving 3 student objects");
        studentDAO.save(tempStudent1);
        studentDAO.save(tempStudent2);
        studentDAO.save(tempStudent3);
    }

    private void createStudent(StudentDAO studentDAO) {

        //create the student object
        System.out.println("hey creating a new student");
        Student tempStudent = new Student("Shree","Hari","hari@108.com");

        //save the student object
        System.out.println("hey saving the student");
        studentDAO.save(tempStudent);

        //display the student id
        System.out.println("hey displaying the student id: "+tempStudent.getId());

    }


}
