package com.brightflag.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brightflag.domain.Student;
import com.brightflag.service.StudentService;

@RestController()
@RequestMapping("/api/students/")
public class StudentAPIController {
	private static Logger log = LogManager.getLogger(StudentAPIController.class.getName());

	@Autowired
	StudentService studentService;

	/*
	 * @GetMapping() public List<Student> getStudents() { return
	 * studentService.getStudents(); }
	 */
	
	@GetMapping("getStudentsBySubjectID/{subjectID}")
	public List<Student> getStudentsBySubjectID(@PathVariable("subjectID") int subjectID ) {
		log.info("Attempting to get all students who study a given subject with the subject id " + subjectID);
		
		return studentService.getStudentsBySubjectID(subjectID);
	}
	
}
