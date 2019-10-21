package com.brightflag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brightflag.domain.Student;
import com.brightflag.interfaces.IStudentService;
import com.brightflag.repository.StudentRepository;
import com.brightflag.repository.SubjectToStudentRepository;

@Service
public class StudentService implements IStudentService {
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	SubjectToStudentRepository subjectToStudentRepository;

	public List<Student> getStudents() {
		return studentRepository.getStudents();
	} 
	
	public Student getStudent(String firstName, String lastName) {
		return studentRepository.getStudent(firstName, lastName);
	}
	
	public List<Student> getStudentsBySubjectID(int subjectID) {
		return subjectToStudentRepository.getStudentsBySubjectID(subjectID);
	}
}
