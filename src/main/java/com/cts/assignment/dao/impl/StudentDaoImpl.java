package com.cts.assignment.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.assignment.dao.StudentDao;
import com.cts.assignment.entity.Student;


@Service
public class StudentDaoImpl implements StudentDao{
	
	private static final String FIND_ALL_STUDENT_SQL = "SELECT * FROM STUDENT";
	private static final String INSERT_STUDENT_SQL = "INSERT INTO STUDENT(FIRST_NAME, LAST_NAME) VALUES(?,?)";
	private static final String GET_STUDENT_BY_NAME = "SELECT * FROM STUDENT WHERE LOWER(FIRST_NAME) = ? AND LOWER(LAST_NAME) = ?";
	private static final String DELETE_STUDENT_BY_ID = "DELETE FROM STUDENT WHERE ID = ?";
	private static final String UPDATE_STUDENT = "UPDATE STUDENT SET FIRST_NAME = ? , LAST_NAME = ? WHERE ID = ?";
	
	private final DataSource dataSource;
	
	//injecting DataSource through constructor injection
	@Autowired
	public StudentDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean addStudent(Student student) {
		
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement prep = conn.prepareStatement(INSERT_STUDENT_SQL);
			prep.setString(1, student.getFirstName());
			prep.setString(2, student.getLastName());
			int response = prep.executeUpdate();
			return response>0;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
	
		return false;
	
	}

	@Override
	public List<Student> findAllStudent() {
		List<Student> studentList=null;
		PreparedStatement statement = null;
		ResultSet resultSet=null;
		try {
			Connection connection = dataSource.getConnection();
			statement = connection.prepareStatement(FIND_ALL_STUDENT_SQL);
			resultSet = statement.executeQuery();
			studentList=new ArrayList<>();
			while (resultSet.next()) {
				studentList.add(new Student(
						resultSet.getInt("ID"),
						resultSet.getString("FIRST_NAME"), 
						resultSet.getString("LAST_NAME")));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				resultSet.close();
				statement.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}

		return studentList;
	}

	@Override
	public List<Student> findStudentByName(String first_name, String last_name) {
		List<Student> foundStudents = new ArrayList<>();
		
		try {
			
			Connection conn = dataSource.getConnection();
			PreparedStatement prep = conn.prepareStatement(GET_STUDENT_BY_NAME);
			prep.setString(1, first_name.toLowerCase());
			prep.setString(2, last_name.toLowerCase());
			
			ResultSet result = prep.executeQuery();
			while(result.next()) {
				Student student = new Student(result.getInt("ID"), result.getString("FIRST_NAME"), result.getString("LAST_NAME"));
				foundStudents.add(student);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return foundStudents;
	}

	@Override
	public boolean deleteStudentById(int id) {
		
		try {
			
			Connection conn = dataSource.getConnection();
			PreparedStatement prep = conn.prepareStatement(DELETE_STUDENT_BY_ID);
			prep.setInt(1, id);
			
			int result = prep.executeUpdate();
			
			return result>0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return false;
	}

	@Override
	public boolean updateStudent(Student student) {
		
		try {
			
			Connection conn = dataSource.getConnection();
			PreparedStatement prep = conn.prepareStatement(UPDATE_STUDENT);
			prep.setString(1,student.getFirstName());
			prep.setString(2,student.getLastName());
			prep.setInt(3, student.getId());
			
			int result = prep.executeUpdate();
			
			return result>0;
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
