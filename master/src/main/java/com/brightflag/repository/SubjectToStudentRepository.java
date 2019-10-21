package com.brightflag.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.brightflag.domain.Student;

@Repository
public class SubjectToStudentRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private final String sqlGetStudentsBySubjectID = 
			"SELECT student.firstName, student.LastName, student.studentID FROM student, subjectToStudent "
			+ "WHERE subjectToStudent.subjectID = ? AND student.studentID = subjectToStudent.studentID";
	private final String sqlGetAStudentsSubjectsByID = "SELECT * FROM subjectToStudent WHERE studentID = ?";
	private final String sqlAssignStudentToSubject = "INSERT INTO subjectToStudent VALUES(?, ?)";
	private final String sqlIsStudentAssignedToSubject = "SELECT count(*) FROM subjectToStudent WHERE studentID = ? AND subjectID = ?";
	
	public int assignStudentToSubject(Integer studentID, Integer subjectID) {
		return  jdbcTemplate.update(sqlAssignStudentToSubject, studentID, subjectID );
	}
	
	public boolean isStudentAssignedSubject(Integer studentID, Integer subjectID) {
		Integer count = jdbcTemplate.queryForObject(sqlIsStudentAssignedToSubject, new Object[] {studentID, subjectID}, 
				Integer.class);
		
		return count > 0;
	}
	
	public List<Student> getStudentsBySubjectID(int subjectID) {
		return jdbcTemplate.query(sqlGetStudentsBySubjectID, new Object[] {subjectID}, 
				new BeanPropertyRowMapper<Student>(Student.class));
	}
	
}
