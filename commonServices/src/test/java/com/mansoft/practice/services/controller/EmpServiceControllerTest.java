package com.mansoft.practice.services.controller;

//static import allows for more concise code (createMock etc.)
import static org.easymock.EasyMock.createMock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.mansoft.practice.services.controllers.EmpServiceController;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmpServiceControllerTest {

	@Autowired
	private MockMvc mvc;
	
	

	
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;
	@InjectMocks
	EmpServiceController empServiceController;
  @Before
  public void setup()
  {
	  empServiceController=new EmpServiceController();
	  request=createMock(HttpServletRequest.class);
	 

  }
	@Test
	public void addEmployeeWithBadXML() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.post("/employee/add")
				.contentType("application/xml")
				.content("");

		
		this.mvc.perform(builder).andExpect(
				MockMvcResultMatchers.status().is(400));

	}
	
	

	

	private static String createEmployeeInXml(String id, String name,
			String joiningdate, String department) {
		return "<employee>" + "<id>" + id + "</id>" + "<name>" + name
				+ "</name>" + "<joiningdate>" + joiningdate + "</joiningdate>"
				+ "<department>" + department + "</department>" + "</employee>";
	}
	
	

}