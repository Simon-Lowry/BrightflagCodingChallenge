package com.brightflag.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.brightflag.domain.Grade;

@Repository
public class GradeToStudentRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static Logger log = LogManager.getLogger(GradeToStudentRepository.class.getName());
	
	private final String sqlGetGradesByStudentID = 
			"SELECT * FROM grade, gradeToStudent WHERE gradeToStudent.studentID = ? AND gradeToStudent.subjectID = ?"
			+ " AND grade.gradeID = gradeToStudent.gradeID";
	
	public Grade getStudentsBySubjectID(int studentID, int subjectID) {
		Grade grade;
		try { 
			grade = jdbcTemplate.queryForObject(sqlGetGradesByStudentID, new Object[] {studentID, subjectID}, 
				new BeanPropertyRowMapper<Grade>(Grade.class));
		}catch(EmptyResultDataAccessException ex) {
			log.info("No grade for student with id: " + studentID + " for the subjectID: "+ subjectID );
			return null;
		}
		return grade;
	}
}
