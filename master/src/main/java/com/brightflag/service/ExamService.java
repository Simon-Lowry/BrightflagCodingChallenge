package com.brightflag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightflag.repository.StudentRepository;

@Service
public class ExamService {

	@Autowired
	StudentRepository studentRepository;
	
	
}
