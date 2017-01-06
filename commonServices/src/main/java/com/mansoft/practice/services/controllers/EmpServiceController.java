package com.mansoft.practice.services.controllers;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mansoft.practice.services.domain.EmployeeDO;
import com.mansoft.practice.services.exception.MyServiceException;
import com.mansoft.practice.services.intf.EmpServiceIntf;
import com.mansoft.practice.services.misc.ResponseMessage;
import com.mansoft.practice.services.model.Employee;
import com.mansoft.practice.services.utils.MyServiceConstants;

/**
 * @author mandar_auti
 *
 */
@RestController
@RequestMapping(value = "/employee")
public class EmpServiceController implements MyServiceConstants {
Logger logger=LoggerFactory.getLogger(EmpServiceController.class);
	@Resource(name = "empService")
	EmpServiceIntf emplServiceIntf;
	
	/** Rest Endpoint for Adding new employee
	 * @param employee
	 * @param request
	 * @param response
	 * @return ResponseMessage with Status Ok if successful else failure status code.
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/xml")
	public ResponseMessage addEmployee(@Valid @RequestBody EmployeeDO employee,
			HttpServletRequest request, HttpServletResponse response,BindingResult results){
		logger.info("Input Data :" +employee);
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		ResponseMessage responseMessage = new ResponseMessage();
		try {
			if(null==employee){
		        
				logger.info("Null Employee");
				throw new MyServiceException(BAD_DATA);
		        
		    }
			mandatoryFieldValidation(employee,response);
			Employee employeeEntity = new Employee();
			employeeEntity.setEmpName(employee.getName());
			employeeEntity.setEmpDepartment(employee.getDepartment().name());

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy",Locale.US);
			Date date = dateFormat.parse(employee.getJoiningdate());
			long time = date.getTime();
			employeeEntity.setEmpJoiningdate(new Timestamp(time));
			employeeEntity.setEmpDepartment(employee.getDepartment().name());
			employeeEntity.setMdyTS(new Timestamp(new Date().getTime()));
			logger.info("Employee name" + employee.getName());
			emplServiceIntf.addEmployee(employeeEntity);
			responseMessage.setStatusCode(HttpStatus.OK.getReasonPhrase());

		} catch (Exception exception) {
			responseMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR
					.getReasonPhrase());
				exception.printStackTrace();
				
			
		}
		stopWatch.stop();
		response.setHeader("RestUrl",request.getRequestURL().toString() );
		response.setHeader("executionTime", stopWatch.getTotalTimeMillis() + " ms");
		return responseMessage;
	}

	/** Rest Endpoint for updating employee
	 * @param employee
	 * @param request
	 * @param response
	 * @return ResponseMessage with Status Ok if successful else failure status code.
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/xml")
	public ResponseMessage updateEmployee(@RequestBody EmployeeDO employee,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Input Data :" +employee);
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		ResponseMessage responseMessage = new ResponseMessage();
		try {
                 if(null==employee){
		        
				logger.info("Null Employee");
				throw new MyServiceException(BAD_DATA);
		        
		    }
			if (null == employee.getId()) {
				throw new MyServiceException(MANDATORY_FIELD_MISSING_CODE + ":"
						+ MANDATORY_FIELD_MISSING_MESSAGE + " Emp Id");
			}
			
			Employee employeeEntity = new Employee();
			employeeEntity.setEmpId(Integer.valueOf(employee.getId()));
			employeeEntity.setEmpName(employee.getName());
			employeeEntity.setEmpDepartment(employee.getDepartment().name());

			DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
			Date date = dateFormat.parse(employee.getJoiningdate());
			long time = date.getTime();
			employeeEntity.setEmpJoiningdate(new Timestamp(time));
			employeeEntity.setEmpDepartment(employee.getDepartment().name());
			employeeEntity.setMdyTS(new Timestamp(new Date().getTime()));
			System.out.println("Employee name" + employee.getName());
			emplServiceIntf.updateEmployee(employeeEntity);
			responseMessage.setStatusCode(HttpStatus.OK.getReasonPhrase());

		} catch (Exception exception) {
			responseMessage.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR
					.getReasonPhrase());
			responseMessage.setErrorMessage(exception.getMessage());
			exception.printStackTrace();
		}
		stopWatch.stop();
		response.setHeader("RestUrl",request.getRequestURL().toString() );
		response.setHeader("executionTime", stopWatch.getTotalTimeMillis() + " ms");
		return responseMessage;
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	void handleXMLException(HttpMessageNotReadableException ex) {

		String error = "Invalid XML.Error is:-";
		error = error + ex.getMessage();
		String responseMessage = "FAILOVER";

		System.out.println("error" + error);
	}
	
	private boolean isProperDateFormat(String date)
	{
		boolean result=true;
		try{
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy",Locale.US);
		Date joiningDate = dateFormat.parse(date);
		}catch(ParseException parseException)
		{
			result=false;
			
		}
		return result;
	}
	
	private void mandatoryFieldValidation(EmployeeDO employeeDO,HttpServletResponse response) throws MyServiceException
	{
		if (null == employeeDO.getJoiningdate()) {
		
			throw new MyServiceException(MANDATORY_FIELD_MISSING_CODE + ":"
					+ MANDATORY_FIELD_MISSING_MESSAGE + "Joining date()");
			
			
		}
		if (null == employeeDO.getName()|| StringUtils.isEmpty(employeeDO.getName())) {
			
			throw new MyServiceException(MANDATORY_FIELD_MISSING_CODE + ":"
					+ MANDATORY_FIELD_MISSING_MESSAGE + "Name");
		}
		if (null == employeeDO.getDepartment()) {
			
			throw new MyServiceException(MANDATORY_FIELD_MISSING_CODE + ":"
					+ MANDATORY_FIELD_MISSING_MESSAGE + "Department");
		}
	}

}
