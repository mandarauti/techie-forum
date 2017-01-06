package com.mansoft.practice.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmpServiceTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void addEmployee() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.post("/employee/add")
				.contentType("application/xml")
				.content(createEmployeeInXml("1", "Mandar", "12/12/1978", "it"));

		// create one more user
		this.mvc.perform(builder).andExpect(
				MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void updateEmployeeWithin24() throws Exception {

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.post("/employee/add")
				.contentType("application/xml")
				.content(createEmployeeInXml("1", "Mandar", "12/12/1978", "it"));
		this.mvc.perform(builder);
		builder = MockMvcRequestBuilders
				.post("/employee/update")
				.contentType("application/xml")
				.content(createEmployeeInXml("1", "Mandar", "12/12/1978", "it"));

		// create one more user
		this.mvc.perform(builder).andExpect(
				MockMvcResultMatchers.status().isOk());

	}

	private static String createEmployeeInXml(String id, String name,
			String joiningdate, String department) {
		return "<employee>" + "<id>" + id + "</id>" + "<name>" + name
				+ "</name>" + "<joiningdate>" + joiningdate + "</joiningdate>"
				+ "<department>" + department + "</department>" + "</employee>";
	}

}