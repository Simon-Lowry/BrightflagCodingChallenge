package com.brightflag.interfaces;

import java.util.List;

import com.brightflag.domain.Student;

public interface IStudentService {

	public List<Student> getStudents();
	
	public Student getStudent(String firstName, String lastName);
	
	public List<Student> getStudentsBySubjectID(int subjectID);
}
