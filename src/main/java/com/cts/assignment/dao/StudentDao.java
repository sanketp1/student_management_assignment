package com.cts.assignment.dao;

import java.util.List;

import com.cts.assignment.entity.Student;


public interface StudentDao {
	/*
	 * @method: addStudent
	 * @param: Student
	 * @return: for successfully adding student will return true otherwise false
	 */
	boolean addStudent(Student student);
	
	/*
	 * @method:findAllStudent
	 * @return: return List of student  
	 */
	List<Student> findAllStudent();
	
	/*
	 * @method: findStudentByName
	 * @param: two string first_name and last_name
	 * @return: will return List of student whose first_name and last_name matches
	 */
	List<Student> findStudentByName(String first_name, String last_name);
	
	/*
	 * @method: deleteStudentByName
	 * @param: an id of integer type
	 * @return: for successfully deleting student will return true otherwise false
	 */
	boolean deleteStudentById(int id);
	
	/*
	 * @method: updateStudent
	 * @param: Student
	 * @return: for successfully updating student will return true otherwise false
	 */
	boolean updateStudent(Student student);

}

