package com.bhavuk.cruddemo;

import com.bhavuk.cruddemo.dao.AppDao;
import com.bhavuk.cruddemo.entity.Course;
import com.bhavuk.cruddemo.entity.Instructor;
import com.bhavuk.cruddemo.entity.InstructorDetail;
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
			deleteCourse(appDao);
		};
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
