package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory =new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		//create session
		Session session= factory.getCurrentSession();
		
		try {

			// start a transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudents=session.createQuery("from Student").getResultList();
			
			// display the students
			printStudents(theStudents);
			
			// query students with last name Doe
			theStudents=session.createQuery("from Student s where s.lastName='Doe'")
					.getResultList();
			
			System.out.println("\n\nStudents who have last name Doe");
			printStudents(theStudents);
			
			// query students with last name Doe OR first name Daffy			
			theStudents=session.createQuery("from Student s where s.lastName='Doe'OR s.firstName='Daffy'")
					.getResultList();
			System.out.println("\nStudents with last name Doe OR first name Daffy");
			printStudents(theStudents);
			
			// query Students with email ending with .com			
			theStudents = session.createQuery("from Student s "
					+ "where s.email LIKE '%.com'")
					.getResultList();
			
			System.out.println("Students with email ending with .com");
			printStudents(theStudents);
			
			
			
			
			
			
			
			
			
			
			// commit transaction
			 session.getTransaction().commit();
			 
			 System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

	private static void printStudents(List<Student> theStudents) {
		for ( Student tempStudent:theStudents) {
			System.out.println(tempStudent);
		}
	}

}