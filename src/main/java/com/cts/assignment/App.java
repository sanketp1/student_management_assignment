package com.cts.assignment;

import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cts.assignment.dao.StudentDao;
import com.cts.assignment.dao.impl.StudentDaoImpl;
import com.cts.assignment.entity.Student;

/**
 * Hello world!
 *
 */
public class App 
{

    private static Scanner sc = new Scanner(System.in);;
    private static ApplicationContext context = new  AnnotationConfigApplicationContext(AppConfig.class);

    private static DataSource source = context.getBean(DataSource.class);
    //getting student Dao
    private static StudentDao studentDao = new StudentDaoImpl(source);

    public static void main( String[] args )
    {

        int choice;
        do{
        	System.out.println();
            System.out.println("--------------------Student Management System-------------------");
            System.out.println();
            System.out.println("Please choose following option: ");
            System.out.println("1. Add Student");
            System.out.println("2. Fetch All Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Get Student By Name");
            System.out.println("5. Update Student");
            System.out.println("99. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            System.out.println();

            switch (choice){
                case 1:
                    addStudent();
                    break;
                case 2:
                    fetchAllStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    getStudentByName();
                    break;
                case 5:
                    updateStudent();
                    break;
                case 99:
                    //closing input stream
                    sc.close();
                    System.out.println("Thanks for visiting.....");
                    break;
                default:
                    System.out.println("Please enter valid choice");
                    break;

            }

        }
        while (choice!=99);

        
        
        
    }

    private static void updateStudent(){
    	System.out.println("--------Updating Student---------");
    	
    	System.out.print("Enter ID of Student : ");
    	int id = sc.nextInt();
    	
    	System.out.println();
    	
    	System.out.print("Enter new first name: ");
    	String fname = sc.next();
    	
    	System.out.println();
    	
    	System.out.print("Enter new last name: ");
    	String lname = sc.next();
    	
    	System.out.println();
    	
    	Student student = new Student(id,fname,lname);
    	
    	boolean result = studentDao.updateStudent(student);
    	
    	if(result) {
    		System.out.println("Successfully updated student");
    	}else {
    		System.out.println("An Error occurred while updating student");
    	}
    }
    
    
    
    private static  void getStudentByName(){    	
     	System.out.println("--------Seaching Student By Name---------");
    	System.out.print("Enter first name: ");
    	String fname = sc.next();
    	
    	System.out.println();
    	
    	System.out.print("Enter last name: ");
    	String lname = sc.next();
    	
    	System.out.println();
    	
    	List<Student> found = studentDao.findStudentByName(fname, lname);
    	
    	System.out.println("Found Results");
    	System.out.println("ID | FIRST NAME | LAST NAME");
    	found.forEach(student-> System.out.println(student.getId()+" "+ student.getFirstName()+" "+student.getLastName()));
    	
    }
    
    private static void deleteStudent() {
    	System.out.println("--------------Deleting Student By ID--------------");
    	System.out.print("Enter ID of student: ");
    	int id = sc.nextInt();
    	System.out.println();
    	
    	boolean result = studentDao.deleteStudentById(id);
    	
    	if(result) {
    		System.out.println("Successfully deleted student");
    	}else {
    		System.out.println("An error occurred while deleting student");
    	}
    	
    }

    private static void fetchAllStudent() {
    	System.out.println("-----------------Fetching All Students--------------------");
        //getting all student from database
    	System.out.println("ID | First Name | Last Name");
        List<Student> students = studentDao.findAllStudent();
        students.forEach(student-> System.out.println(student.getId()+" "+student.getFirstName()+" "+student.getLastName()));
    }

    private static void addStudent() {
    	System.out.println("----------------Adding Student------------------");
        System.out.print("Enter First Name: ");
        String fname = sc.next();
        System.out.println();

        System.out.print("Enter Last Name: ");
        String lname = sc.next();
        System.out.println();

        //creating object of student for inserting it inside the database
        Student student = new Student(fname,lname);
        boolean result =  studentDao.addStudent(student);
        if (result){
            System.out.println("Successfully added "+fname+" "+lname);
        }else{
            System.out.println("An error occurred while adding "+fname+" "+lname);
        }

    }
}
