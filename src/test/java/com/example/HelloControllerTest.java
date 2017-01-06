package com.example;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	@Ignore
	public void addEmployee() throws Exception {
		MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/employee/add")
                                      .contentType(MediaType.APPLICATION_XML)
                                      .content(createEmployeeInXml(
                                                          "1",
                                                          "Mandar",
                                                          "12/12/78",
                                                          "it"));

//create one more user
this.mvc.perform(builder)
        .andExpect(MockMvcResultMatchers.status()
                                        .isCreated());

	}
	
	private static String createEmployeeInXml (String id, String name, String joiningdate,String department) {
        return "<employee>" +
        "<id>" + id + "</id>" +
        "<name>" + name + "</name>" +
        "<joiningdate>" + joiningdate + "</joiningdate>" +
        "<department>" + department + "</department>" +
        "</employee>";
    }
}