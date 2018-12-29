package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		
		Session session = factory.getCurrentSession();
		
		try {
			
			
			//start transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
					
			//display students
			diplayStudents(theStudents);
			
			//query students: lastName='Mo'
			theStudents = session.createQuery("from Student s where s.lastName='Mo'").getResultList();
			
			//display students who have lastName='Mo'
			System.out.println("\n\nStudents who have last name with 'Mo'");
			diplayStudents(theStudents);
			
			
			//query students: lastName='Mo'
			theStudents = session.createQuery("from Student s where s.lastName='Mo'"
					+" OR s.firstName='Daffy'").getResultList();
			
			//display students who have lastName='Mo'
			System.out.println("\n\nStudents who have last name with 'Mo' or firs name 'Daffy' ");
			diplayStudents(theStudents);
			
			
			//query students: have like email '@luv2code.com'
			
			theStudents = session.createQuery("from Student s where"
					+ " s.email LIKE '%luv2code.com'").getResultList();
					
			
			//display students who have lastName='Mo'
			System.out.println("\n\nStudents who have like email '@luv2code.com' ");
			diplayStudents(theStudents);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			
			factory.close();
			
		}

	}

	private static void diplayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
