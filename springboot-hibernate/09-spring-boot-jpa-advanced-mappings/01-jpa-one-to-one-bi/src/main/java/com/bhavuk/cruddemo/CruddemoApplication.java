package com.bhavuk.cruddemo;

import com.bhavuk.cruddemo.dao.AppDao;
import com.bhavuk.cruddemo.entity.Instructor;
import com.bhavuk.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
//			findInstructorDetail(appDao);
			deleteInstructorDetail(appDao);
		};
	}

	private void deleteInstructorDetail(AppDao appDao) {

		System.out.println("Deleting instructor details...");
		int theId = 4;
        System.out.println("Deleting instructor detail id: "+theId);

        appDao.deleteInstructorDetailById(theId);

        System.out.println("Deleted instructor detail!!!");
	}

	private void findInstructorDetail(AppDao appDao) {

		//get the instructor detail object
		int theId = 2;
		InstructorDetail tempInstructorDetail = appDao.findInstructorDetailById(theId);

		//print the instructor detail
		System.out.println("instructor details: "+tempInstructorDetail);

		//print the associate instructor
		System.out.println("associated instructor: "+tempInstructorDetail.getInstructor());

		System.out.println("Done!!!");
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
