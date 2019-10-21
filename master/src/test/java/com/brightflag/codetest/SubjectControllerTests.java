package com.brightflag.codetest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.brightflag.controller.SubjectAPIController;
import com.brightflag.interfaces.ISubjectService;
import com.fasterxml.jackson.databind.ObjectMapper;

/*@RunWith(SpringRunner.class)
@WebMvcTest(SubjectAPIController.class)
@ActiveProfiles(profiles = "local")*/

/*@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com.brightflag"})
@SpringBootTest
@WebMvcTest(SubjectAPIController.class)*/

@RunWith(SpringRunner.class)
@WebMvcTest(SubjectAPIController.class)
public class SubjectControllerTests {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ISubjectService mockSubjectService;


	@Test 
	public void GivenPostTo_assignStudentToSubject_WithValidData_ThenReturnStatusOK() throws Exception {
		Map<String, String> request = new HashMap<String, String>();
		request.put("name", "bill clinton");
		request.put("subjectID", "1");
		
		when(mockSubjectService.assignStudentToSubject(any(String.class), any(Integer.class))).thenReturn(1);
		
		mvc.perform(post("/api/subjects/assignToSubject/").contentType(MediaType.APPLICATION_JSON).
				content(asJsonString(request))).andExpect(status().isOk());
		
	}
	
	@Test 
	public void GivenPostTo_assignStudentToSubject_WithInvalidData_ThenReturn500ErrorStatus() throws Exception {
		Map<String, String> request = new HashMap<String, String>();
		request.put("name", "bill clinton");
		request.put("subjectID", "1");
		
		when(mockSubjectService.assignStudentToSubject(any(String.class), any(Integer.class))).thenReturn(0);
		
		mvc.perform(post("/api/subjects/assignToSubject/").contentType(MediaType.APPLICATION_JSON).
				content(asJsonString(request))).andExpect(status().is5xxServerError());
		
	}
	
	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
