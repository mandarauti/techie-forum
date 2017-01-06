package com.mansoft.practice.services.client;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mansoft.practice.services.client.domain.EmployeeDO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Ignore
public class RestConsumerTest {

	@Autowired
	private RestConsumer remoteCallService;

	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;
	@Mock
	EmployeeDO employeeDO;

	@Test
	public void testRetry() throws Exception {
		boolean value = this.remoteCallService
				.addEmployee(employeeDO, request, response);
		verify(remoteCallService, times(3)).addEmployee(employeeDO, request,
				response);

		assertThat(value, is(true));
	}

	@Configuration
	@EnableRetry
	public static class SpringConfig {
		@Mock
		HttpServletRequest request;
		@Mock
		HttpServletResponse response;

		@Bean
		public RestConsumer remoteCallService() throws Exception {
			EmployeeDO employeeDO = new EmployeeDO();
			ResponseEntity<ResponseMessage> resMsg = new ResponseEntity<ResponseMessage>(
					HttpStatus.OK);
			RestConsumer remoteService = mock(RestConsumer.class);
			when(remoteService.addEmployee(employeeDO, request, response))
					.thenThrow(new RuntimeException("Remote Exception 1"))
					.thenThrow(new RuntimeException("Remote Exception 2"))
				    .thenReturn(true);
					
			return remoteService;
		}
	}

}
