package com.brightflag.domain;

import java.util.List;

public class Subject {
	private Integer subjectID;
	private String subjectName;
	private Grade grade;
	private List<Student> students;
	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Integer getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(Integer subjectID) {
		this.subjectID = subjectID;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	public Grade getGrade() {
		return grade;
	}

	public void setGrades(Grade grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Subject [subjectID=" + subjectID + ", subjectName=" + subjectName + "]";
	}

}
