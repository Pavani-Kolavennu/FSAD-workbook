package com.klu.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.klu.config.AppConfig;
import com.klu.model.Student;

public class MainApp {

	public static void main(String[] args) 
	{
		ApplicationContext xmlContext = new ClassPathXmlApplicationContext("bean.xml");
		Student s1 = (Student) xmlContext.getBean("StudentInfo");
		System.out.println("Student Details using XML configuration");
		s1.display();
		System.out.println("-----------------------------------------");
		ApplicationContext AnnContext = new AnnotationConfigApplicationContext(AppConfig.class);
		Student s2 = (Student) AnnContext.getBean(Student.class);
		System.out.println("Student Details using Annotation configuration");
		s2.display();
		
	}

}