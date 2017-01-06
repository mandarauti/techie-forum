package com.mansoft.practice.services.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.policy.TimeoutRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mansoft.practice.services.client.domain.EmployeeDO;

/**
 * @author mandar_auti
 *
 */

@RestController
@RequestMapping(path = "/employee")
public class RestConsumer {
	
private TimeoutRetryPolicy policy = null;
	
	
	private static String BASEURL = "http://localhost:8080/employee/";
	private static final Logger log = LoggerFactory
			.getLogger(RestConsumer.class);

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/xml")
	public boolean addEmployee(
			@RequestBody EmployeeDO employeeDO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		try{
			RestTemplate restTemplate = new RestTemplate();
			// Use the policy...
			RetryTemplate template = getRetryTemplate(10);
			template.execute(new RetryCallback<ResponseEntity<ResponseMessage>,Exception>() {
			    public ResponseEntity<ResponseMessage> doWithRetry(RetryContext context) {
			    	
			    	System.out.println("Attempt#"+context.getRetryCount());
			    	 return(restTemplate
							.postForEntity(BASEURL+"add",
									employeeDO, ResponseMessage.class));
				
			    }
			});
			
			
	}catch(Exception ex)
	{
		log.error(ex.getMessage());
		
		ex.printStackTrace();
		throw ex;
		
	}
		return true;	
		}



	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/xml")
		public boolean updateEmployee(
			@RequestBody EmployeeDO employeeDO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try{
			RestTemplate restTemplate = new RestTemplate();
			// Use the policy...
			RetryTemplate template = getRetryTemplate(10);
			template.execute(new RetryCallback<ResponseEntity<ResponseMessage>,Exception>() {
			    public ResponseEntity<ResponseMessage> doWithRetry(RetryContext context) {
			    	
			    	log.info("Attempt#"+context.getRetryCount());
			    	 return(restTemplate
							.postForEntity(BASEURL+"update",
									employeeDO, ResponseMessage.class));
				
			    }
			});
			
			
	}catch(Exception ex)
	{
		log.error(ex.getMessage());
		
		ex.printStackTrace();
		throw ex;
		
	}
		return true;	

	}

	
	
	private static RetryTemplate getRetryTemplate(Integer timeoutInSec) {
		// we set the retry time out
		TimeoutRetryPolicy retryPolicy = new TimeoutRetryPolicy();
		retryPolicy.setTimeout(timeoutInSec * 1000 * 60);

		// we set the back off period to 60s
		FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
		backOffPolicy.setBackOffPeriod(60000);
		SimpleRetryPolicy simpleRetryPolicy=new SimpleRetryPolicy();
		simpleRetryPolicy.setMaxAttempts(3);
		
		// our retry service
		RetryTemplate retryTemplate = new RetryTemplate();
		retryTemplate.setRetryPolicy(retryPolicy);
		retryTemplate.setBackOffPolicy(backOffPolicy);
		retryTemplate.setRetryPolicy(simpleRetryPolicy);
		return retryTemplate;
	}
	 

}
