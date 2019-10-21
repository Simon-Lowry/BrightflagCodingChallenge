package com.brightflag.codetest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.brightflag.domain.Grade;
import com.brightflag.domain.Student;
import com.brightflag.interfaces.IStudentService;
import com.brightflag.repository.GradeToStudentRepository;
import com.brightflag.repository.SubjectRepository;
import com.brightflag.repository.SubjectToStudentRepository;
import com.brightflag.service.SubjectService;

import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
@SpringBootTest
public class SubjectServiceTests {
	
	@Mock
	IStudentService mockStudentService;
	
	@Mock
	SubjectToStudentRepository mockSubjectToStudentRepository;
	
	@Mock
	SubjectRepository mockSubjectRepository;
	
	@Mock
	GradeToStudentRepository mockGradeToStudentRepository;
	
	@InjectMocks
	SubjectService subjectService;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setUp() {
		subjectService = new SubjectService();
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void GivenValidStudentIDAndSubjectID_WhenisStudentAssignedSubjectCalled_ReturnTrue() {
		// Arrange
		int subjectID = 1;
		int studentID = 1;
		
		when(mockSubjectToStudentRepository.isStudentAssignedSubject(any(Integer.class), any(Integer.class))).thenReturn(true);
		
		// Act
		boolean result = subjectService.isStudentAssignedSubject(studentID, subjectID);
		
		// Assert
		assertTrue(result);
		verify(mockSubjectToStudentRepository, times(1)).isStudentAssignedSubject(any(Integer.class), any(Integer.class));
	}
	
	
	@Test
	public void GivenInvalidStudentIDAndSubjectID_WhenisStudentAssignedSubjectCalled_ReturnFalse() {
		// Arrange
		int subjectID = 1;
		int studentID = 1;
		
		when(mockSubjectToStudentRepository.isStudentAssignedSubject(any(Integer.class), any(Integer.class))).thenReturn(false);
		
		// Act
		boolean result = subjectService.isStudentAssignedSubject(studentID, subjectID);
		
		// Assert
		assertFalse(result);
		verify(mockSubjectToStudentRepository, times(1)).isStudentAssignedSubject(any(Integer.class), any(Integer.class));
	}

	// getAllStudentsForSubject tests
	@Test
	public void GivenSubjectID_WhenGetAllStudentsForSubjectCalled_AndNoStudentsReturned_ReturnNull() {
		// Arrange
		int subjectID = 1;
		when (mockSubjectToStudentRepository.getStudentsBySubjectID(any(Integer.class))).thenReturn(null);
		
		// Act
		List<Student> students = subjectService.getAllStudentsForSubject(subjectID);
		
		// Assert
		assertNull(students);
		verify(mockSubjectToStudentRepository, times(1)).getStudentsBySubjectID(any(Integer.class));
	}
	
	
	// assignStudentToSubject
	@Test(expected = IllegalArgumentException.class)
	public void GivenInvalidName_WhenAssignStudentToSubjectCalled_ThrowIllegalArgumentException(String name) throws Exception {
		thrown.expect(IllegalArgumentException.class);
		subjectService.assignStudentToSubject("BILLLLL<script>", 1);
	}
	
	
	//subjectToStudentRepository.assignStudentToSubject(student.getStudentID(), subjectID);
	@Test
	public void GivenValidName_ForStudentNotAssigned_WhenAssignStuedentToSubjectCalled_ReturnOne() throws Exception {
		// Arrange
		String name = "Mike Stevens";
		int subjectID = 1;
		Student student = new Student();
		student.setFirstName("Mike");
		student.setLastName("Stevens");
		student.setStudentID(1);
		
		when (mockStudentService.getStudent(any(String.class), any(String.class))).thenReturn(student); 
		when(mockSubjectToStudentRepository.isStudentAssignedSubject(any(Integer.class), any(Integer.class))).thenReturn(false);
		when (mockSubjectToStudentRepository.assignStudentToSubject(any(Integer.class), any(Integer.class))).thenReturn(1);
		
		
		// Act
		int value = subjectService.assignStudentToSubject(name, subjectID);
		
		
		// Assert
		assertEquals(value, 1);
		verify(mockSubjectToStudentRepository, times(1)).isStudentAssignedSubject(any(Integer.class), any(Integer.class));
		verify(mockSubjectToStudentRepository, times(1)).assignStudentToSubject(any(Integer.class), any(Integer.class));
	}
	

	@Test
	public void GivenValidName_ForStudentAlreadyAssignedSubject_WhenAssignStuedentToSubjectCalled_ReturnZero() throws Exception {
		// Arrange
		String name = "Mike Stevens";
		int subjectID = 1;
		Student student = new Student();
		student.setFirstName("Mike");
		student.setLastName("Stevens");
		student.setStudentID(1);
		
		when (mockStudentService.getStudent(any(String.class), any(String.class))).thenReturn(student); 
		when(mockSubjectToStudentRepository.isStudentAssignedSubject(any(Integer.class), any(Integer.class))).thenReturn(true);
		
		// Assert
		thrown.expect(Exception.class);
		
		// Act
		subjectService.assignStudentToSubject(name, subjectID);
		
		
		// Assert
		verify(mockSubjectToStudentRepository, times(1)).isStudentAssignedSubject(any(Integer.class), any(Integer.class));
	}
	
	
	@Test
	public void GivenSubjectID_WhenGetAllStudentsForSubject_ReturnStudentListWithGradeSet() {
		// Arrange
		List<Student> students = new ArrayList<Student>();
		Student student = new Student();
		student.setFirstName("Mike");
		student.setLastName("Stevens");
		student.setStudentID(1);
		int subjectID = 1;
		students.add(student);
	
		Grade grade = new Grade();
		grade.setGradeID(1234);
		grade.setGradeName("1st Class Honours");
		
		
		when(mockSubjectToStudentRepository.getStudentsBySubjectID(any(Integer.class))).thenReturn(students);
		when(mockGradeToStudentRepository.getStudentsBySubjectID(1,  subjectID)).thenReturn(grade); 
		
		// Act
		List<Student> results = subjectService.getAllStudentsForSubject(subjectID);
		
		// Assert
		assertNotNull(results);
		assertNotNull(results.get(0));
		
		Student studentResult = results.get(0);
		assertEquals(studentResult.getFirstName(), student.getFirstName() );
		assertEquals(studentResult.getLastName(), student.getLastName() );
		
		assertNotNull(studentResult.getGrade());
		Grade gradeResult = studentResult.getGrade();
		assertTrue(gradeResult.getGradeName().equals(grade.getGradeName()));	
	}
}
