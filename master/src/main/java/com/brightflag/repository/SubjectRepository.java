package com.brightflag.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.brightflag.domain.Subject;

@Repository
public class SubjectRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private final String sqlGetSubjectBySubjectId = "SELECT * FROM subject WHERE subjectId = ?";
	private final String sqlGetAllSubjects = "SELECT * FROM subject;";
	
	
	public Subject getSubjectBySubjectID(int subjectId) {
		Subject subject = jdbcTemplate.queryForObject(sqlGetSubjectBySubjectId, new Object[] {subjectId}, 
				new BeanPropertyRowMapper<Subject>(Subject.class));
		
		return subject;
	}
	
	public List<Subject> getAllSubjects() {
		return jdbcTemplate.query(sqlGetAllSubjects, new BeanPropertyRowMapper<Subject>(Subject.class));
	}
}
