package com.brightflag.interfaces;

import java.util.List;

import com.brightflag.domain.Student;
import com.brightflag.domain.Subject;

public interface ISubjectService {
	
	public int assignStudentToSubject(String name, Integer subjectID) throws Exception;
	
	public List<Subject> getAllSubjects();
	
	public List<Student> getAllStudentsForSubject(int subjectID);
	
	public Subject getSubjectBySubjectID(int subjectID);
}
