package com.brightflag.repository;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.brightflag.domain.Student;

@Repository
public class StudentRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static Logger log = LogManager.getLogger(StudentRepository.class.getName());
	private final String sqlGetStudent = "SELECT studentID, firstName, lastName FROM student WHERE firstName = ? AND lastName = ?";
	
	
	public List<Student> getStudents() {
		return jdbcTemplate.query("SELECT studentID, firstName, lastName FROM student;",
				new BeanPropertyRowMapper<Student>(Student.class));
	} 
		
	public Student getStudent(String firstName, String lastName) {
		Student student;
		try {
			student = jdbcTemplate.queryForObject(sqlGetStudent, new Object[] {firstName, lastName}, 
				new BeanPropertyRowMapper<Student>(Student.class));
		} catch(EmptyResultDataAccessException ex) {
			log.info("Student does not exist: " + firstName + " "+ lastName );
			return null;
		}
		return student;
	} 	
}