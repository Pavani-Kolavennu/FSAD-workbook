package com.klu.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Student 
{
	  private int rollno;
	  private String studentName;  
	  private String courseName;
	  private int year;
  
  public Student(@Value("2")int rollno, @Value("Sitha")String studentName, @Value("FEDF1")String courseName,  @Value("1")int year) 
  {
	  this.rollno=rollno;
	  this.studentName=studentName;
	  this.courseName=courseName;
	  this.year= year;
  }
  @Value("FEDF")
  public void setCourseName(String courseName) 
  {
    this.courseName=courseName;
  }
  @Value("2")
  public void setYear(int year) 
  {
    this.year= year;
  }
  public void display() 
  {
   System.out.println("Rollno : " + rollno);
   System.out.println("Name : " + studentName);
   System.out.println("CourseName : " + courseName);
   System.out.println("Year : " + year);  
  }
}