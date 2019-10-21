package com.brightflag.controller;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brightflag.domain.Student;
import com.brightflag.domain.Subject;
import com.brightflag.interfaces.ISubjectService;

@RestController()
@RequestMapping("/api/subjects/")
public class SubjectAPIController {
	private static Logger log = LogManager.getLogger(SubjectAPIController.class.getName());
	
	@Autowired
	ISubjectService subjectService;

	@PostMapping(value ="assignToSubject/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> assignStudentToSubject(@RequestBody Map<String, String> payload) throws Exception {
		String name = payload.get("name");
		Integer subjectID = Integer.valueOf(payload.get("subjectID"));
		
		log.info("Attempting to assign student to subject with subject ID: " + subjectID + " and name: " + name);
		
		int numRowsAffected = subjectService.assignStudentToSubject(name, subjectID);
		
		if (numRowsAffected > 0) {
			log.info("Successfully assigned student to subject");
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			log.info("Failed to assign student to subject");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("getAllSubjects")
	public List<Subject> getAllSubjects() {
		log.info("Attempting to retrieve all subjects");
		return subjectService.getAllSubjects();
	}
	
	@GetMapping(value ="getAllStudentsForSubject/{subjectID}") 
	public List<Student> getAllStudentsForSubject(@PathVariable("subjectID") int subjectID) {
		log.info("Attempting to retrieve all students for a given subject: " + subjectID );
		
		return subjectService.getAllStudentsForSubject(subjectID);
	}
	
	@GetMapping(value= "getSubject/{subjectID}")
	public Subject getSubjectBySubjectID(@PathVariable("subjectID") int subjectID) {
		log.info("Attempting to get subject for given subject ID: " + subjectID);
		
		return subjectService.getSubjectBySubjectID(subjectID);
	}
}
