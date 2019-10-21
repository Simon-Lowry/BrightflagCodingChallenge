package com.brightflag.domain;

public class Grade {
	private Integer gradeID;
	private String gradeName;

	public Integer getGradeID() {
		return gradeID;
	}


	public void setGradeID(Integer gradeID) {
		this.gradeID = gradeID;
	}


	public String getGradeName() {
		return gradeName;
	}


	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	
	@Override
	public String toString() {
		return "Grade [gradeID=" + gradeID + ", examName=" + gradeName + "]";
	}

}
