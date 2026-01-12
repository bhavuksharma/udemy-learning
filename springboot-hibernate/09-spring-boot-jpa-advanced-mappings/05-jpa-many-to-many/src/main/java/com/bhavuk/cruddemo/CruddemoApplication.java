package com.bhavuk.cruddemo;

import com.bhavuk.cruddemo.dao.AppDao;
import com.bhavuk.cruddemo.entity.*;
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
	public CommandLineRunner commandLineRunner(AppDao appDao) {
		return runner -> {
//			createInstructor(appDao);
//			findInstructor(appDao);

//			deleteInstructor(appDao);
//			createInstructorWithCourses(appDao);
//			findInstructorWithCourses(appDao);
//			findCoursesForInstructor(appDao);
//		    findInstructorWithCoursesJoinFetch(appDao);
//			updateInstructor(appDao);
//			updateCourse(appDao);
//			deleteInstructor(appDao);
//			deleteCourse(appDao);

//			createCourseAndReviews(appDao);
//			retrieveCourseAndReviews(appDao);
//			deleteCourseAndReviews(appDao);
//			createCourseAndStudents(appDao);
//		    findCourseAndStudents(appDao);
//			findStudentAndCourses(appDao);
//			addMoreCoursesForStudent(appDao);
//			deleteCourse(appDao);
			deleteStudent(appDao);
		};
	}

	private void deleteStudent(AppDao appDao) {

		int theId = 1;
		System.out.println("deleting the student: "+ theId);

		appDao.deleteStudentById(theId);

		System.out.println("Done!");
	}

	private void addMoreCoursesForStudent(AppDao appDao) {

		int theId =2;
		Student tempStudent = appDao.findStudentAndCoursesByStudentId(2);

		//create more courses
		Course tempCourse1 = new Course("Rubic's cube - how to speed up");
		Course tempCourse2 = new Course("Atri 2600 - game development");

		//add courses to the student
		tempStudent.addCourse(tempCourse1);
		tempStudent.addCourse(tempCourse2);

		System.out.println("updating student: "+ tempStudent);
		System.out.println("associated courses: "+ tempStudent.getCourses());

		appDao.update(tempStudent);

		System.out.println("Done!");
	}

	private void findStudentAndCourses(AppDao appDao) {
		int theId = 2;

		Student tempStudent = appDao.findStudentAndCoursesByStudentId(2);

		System.out.println("student fetched: "+ tempStudent);
		System.out.println("associated courses: "+ tempStudent.getCourses());

		System.out.println("Done!");

	}

	private void findCourseAndStudents(AppDao appDao) {

		int theId = 10;

		Course tempCourse = appDao.findCourseAndStudentsByCourseId(theId);

		System.out.println("course fetched: "+ tempCourse);
		System.out.println("associated students: "+ tempCourse.getStudents());

		System.out.println("Done!");
	}

	private void createCourseAndStudents(AppDao appDao) {

		//create course
		Course tempCourse = new Course("Pacman - How to score one million points");

		//create students
		Student tempStudent1 = new Student("John","Doe","john@luv2code.com");
		Student tempStudent2 = new Student("Mary","Public","mary@luv2code.com");

		//add students to the course
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);

		//save the course and associated students
		System.out.println("Saving Course: "+ tempCourse);
		System.out.println("associated students: "+ tempCourse.getStudents());

		appDao.save(tempCourse);

		System.out.println("Done!");

	}

	private void deleteCourseAndReviews(AppDao appDao) {

		int theId =10;

		System.out.println("deleting course id: "+ theId);

		appDao.deleteCourseById(theId);

		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDao appDao) {

		//get the course and Reviews
		int theId = 10;
		Course tempCourse = appDao.findCourseAndReviewsByCourseId(theId);

		//print the course
		System.out.println(tempCourse);

		//print the reviews
		System.out.println(tempCourse.getReviews());

		System.out.println("Done!");
	}

	private void createCourseAndReviews(AppDao appDao) {

		//create a course
		Course tempCourse = new Course("Pacman - How to score one million points");

		//add some reviews
		tempCourse.addReview(new Review("Great Course.... loved it"));
		tempCourse.addReview(new Review("Cool Course, Job wel done"));
		tempCourse.addReview(new Review("What a dumb course"));

		//save the course and leverage the cascade all
		System.out.println("Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDao.save(tempCourse);

		System.out.println("Done!");


	}

	private void deleteCourse(AppDao appDao) {

		int theId = 10;

		System.out.println("delete course id: "+ theId);

		appDao.deleteCourseById(theId);

		System.out.println("Done!");

	}

	private void updateCourse(AppDao appDao) {

		int theId=10;

		//find Course
		System.out.println("finding course id: "+ theId);
		Course tempCourse = appDao.findCourseById(theId);

		//update the course
		System.out.println("updating the course id: "+ theId);
		tempCourse.setTitle("Enjoy Simple Things");

		appDao.update(tempCourse);
		System.out.println("Done!");
	}

	private void updateInstructor(AppDao appDao) {

		int theId = 1;

		//find instructor
		System.out.println("finding instructor id: "+theId);
		Instructor tempInstructor = appDao.findInstructorById(theId);

		//update Instructor
		System.out.println("updating instructor id: "+theId);
		tempInstructor.setLastName("TESTER");

		appDao.update(tempInstructor);

		System.out.println("Done!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDao appDao) {

		int theId =1;

		//find instructor
		System.out.println("Finding instructor id: "+ theId);
		Instructor tempInstructor = appDao.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor: "+tempInstructor);
		System.out.println("the associated courses: "+ tempInstructor.getCourses());

		System.out.println("Done!");

	}

	private void findCoursesForInstructor(AppDao appDao) {

		int theId = 1;
		//find instructor by id
		System.out.println("finding instructor with id: "+theId);

		Instructor tempInstructor = appDao.findInstructorById(theId);

		System.out.println("tempInstructor: "+tempInstructor);

		//find courses for instructor
		System.out.println("finding courses for instructor id: "+ theId);
		List<Course> courses = appDao.findCoursesByInstructorId(theId);

		//associate objects
		tempInstructor.setCourses(courses);

		System.out.println("the associated courses: "+ tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void findInstructorWithCourses(AppDao appDao) {

		int theId = 1;
		System.out.println("finding instructor with id: "+theId);

		Instructor tempInstructor = appDao.findInstructorById(theId);

		System.out.println("tempInstructor: "+tempInstructor);
		System.out.println("the associated courses: "+ tempInstructor.getCourses());

	}

	private void createInstructorWithCourses(AppDao appDao) {

		//create instructor
		Instructor tempInstructor =
				new Instructor("Susan","Pubic","susan@learning.com");

		//create instructor details
		InstructorDetail empInstructorDetail =
				new InstructorDetail(
						"http://www.luv2code.com/youtube",
						"Love 2 play Guitar!!!!!");

		//associate the objects
		tempInstructor.setInstructorDetail(empInstructorDetail);

		//create some courses
		Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
		Course tempCourse2 = new Course("The Pinball Master class");

		//add courses to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		//save instructor
		//
		//Note this will also save the instructor
		//because fo CascadeType.PERSIST
		//
		System.out.println("The Instructor: "+ tempInstructor);
		System.out.println("Courses list: "+tempInstructor.getCourses());
		appDao.save(tempInstructor);

		System.out.println("done saving!!");

	}

	private void deleteInstructor(AppDao appDao) {

		int theId=1;
		System.out.println("Deleting  instructor id: "+theId);

		appDao.deleteInstructorById(theId);

		System.out.println("Deleted instructor!!!");
	}

	private void findInstructor(AppDao appDao) {

		int theId=2;
		System.out.println("Finding Instructor id: "+ theId);

		Instructor tempInstructor = appDao.findInstructorById(theId);
		System.out.println("tempInstructor: "+ tempInstructor);
		System.out.println("the associated instructor details: "+ tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDao appDao) {


		/*
		//create instructor
		Instructor tempInstructor =
				new Instructor("Chad","Darby","darby@learning.com");

		//create instructor details
		InstructorDetail empInstructorDetail =
				new InstructorDetail(
						"http://www.luv2code.com/youtube",
						"Love 2 code!!!!!");
		*/

		//create instructor
		Instructor tempInstructor =
				new Instructor("Madhu","Patel","Madhu@learning.com");

		//create instructor details
		InstructorDetail empInstructorDetail =
				new InstructorDetail(
						"http://www.luv2code.com/youtube",
						"Love 2 play Guitar!!!!!");

		//associate the objects
		tempInstructor.setInstructorDetail(empInstructorDetail);

		//save instructor
		//
		//NOTE: this wil also save the details object
		//because of CascadeType.All
		//
		System.out.println("Saving instructor: "+ tempInstructor);
		appDao.save(tempInstructor);


		System.out.println("done saving!!");
	}

}
