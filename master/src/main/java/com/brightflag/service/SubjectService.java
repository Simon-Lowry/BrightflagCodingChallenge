package com.brightflag.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightflag.domain.Grade;
import com.brightflag.domain.Student;
import com.brightflag.domain.Subject;
import com.brightflag.interfaces.IStudentService;
import com.brightflag.interfaces.ISubjectService;
import com.brightflag.repository.GradeToStudentRepository;
import com.brightflag.repository.SubjectRepository;
import com.brightflag.repository.SubjectToStudentRepository;

@Service
public class SubjectService implements ISubjectService{
	private static Logger log = LogManager.getLogger(SubjectService.class.getName());
	
	@Autowired 
	IStudentService studentService;
	
	@Autowired
	SubjectToStudentRepository subjectToStudentRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	GradeToStudentRepository gradeToStudentRepository;
	
	
	public int assignStudentToSubject(String name, Integer subjectID) throws Exception {
		String firstName = "";
		String lastName = "";
		
		if (isValidName(name)) {
			int indexOfSpace = name.indexOf(" ");
			firstName = name.substring(0, indexOfSpace );
			lastName = name.substring(indexOfSpace + 1);
		    log.info("Valid name.");
		} else {
			log.info("Invalid name, throwing illegal argument exception");
			throw new IllegalArgumentException("Illegal characters for student name. Please try again.");
		}
				
		Student student = studentService.getStudent(firstName, lastName);
		
		log.info("Checking to see if the student exists and if they are already assigned to this subject");
		if (student == null  || isStudentAssignedSubject(student.getStudentID(),subjectID)) {	
			log.info("Student either doesn't exist or they are already assigned to this subject");
			throw new Exception("Unable to assign student to subject. Student either doesn't exist or is already assigned to this subject."); 
		}  else {
			log.info("Student exists, attempting to assign to subject");
			return subjectToStudentRepository.assignStudentToSubject(student.getStudentID(), subjectID);
		}
		
	}
	
	// Sanitize user input to ensure that the name entered contains only valid characters, a-z, a space or these characters ' -
	public boolean isValidName(String name) {
		String regx = "^[A-Za-z\\s'-]+$";									
	    Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(name);
	    
	    log.info("Checking if name entered is a valid name: " + name);
	    return matcher.find() && name.contains(" ");
	}
	

	public List<Subject> getAllSubjects() {
		return subjectRepository.getAllSubjects();
	}
	
	public boolean isStudentAssignedSubject(int studentID, int subjectID) {
		boolean assignedToSubject = subjectToStudentRepository.isStudentAssignedSubject(studentID, subjectID);
		
		if (assignedToSubject) {
			log.info("Student is already assigned to this subject");
			return true;
		} else {
			log.info("Student is not assigned to this subject");
			return false;
		}
	}
	
	public List<Student> getAllStudentsForSubject(int subjectID) {
		List<Student> students = subjectToStudentRepository.getStudentsBySubjectID(subjectID);
		
		if (students == null) {
			log.info("No students found for the given subject");
			return null;
		}
		
		log.info("Students have been assigned to this subject");
		
		for (Student student :  students) {					// get the grades for each student for this subject
			int studentID = student.getStudentID();
			log.info("Retrieving and storing grade for student in this subject");
			Grade grade = gradeToStudentRepository.getStudentsBySubjectID(studentID,  subjectID);
			student.setGrade(grade);
		}
		
		return students;
	}
	
	public Subject getSubjectBySubjectID(int subjectID) {
		return subjectRepository.getSubjectBySubjectID(subjectID);
	}

}
