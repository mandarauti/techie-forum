package com.mansoft.practice.services.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mansoft.practice.services.exception.MyServiceException;
import com.mansoft.practice.services.intf.EmpServiceIntf;
import com.mansoft.practice.services.model.Employee;
import com.mansoft.practice.services.model.ExceptionHistory;
import com.mansoft.practice.services.reposiotry.EmployeeDao;
import com.mansoft.practice.services.reposiotry.ExceptionHistoryDao;
import com.mansoft.practice.services.utils.MyServiceConstants;

/**
 * @author mandar_auti
 *
 */
@Component(value = "empService")
public class EmplServiceImpl implements EmpServiceIntf, MyServiceConstants {
	Logger logger = LoggerFactory.getLogger(EmplServiceImpl.class);
	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private ExceptionHistoryDao exceptionHistoryDao;

	@Override
	public boolean addEmployee(Employee employee) throws Exception {
		employeeDao.save(employee);
		return true;
	}

	@Override
	public Employee getEmployee(int empID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateEmployee(Employee employee) throws Exception {
		// if update request comes for employee withing 24 hours of its creation
		// throw exception.
		if (!isWithin24HourRequest(employee.getEmpId())) {
			employeeDao.save(employee);
		} else {
			ExceptionHistory exceptionHistory = new ExceptionHistory();
			exceptionHistory.setCrtTs(new Timestamp(new Date().getTime()));
			exceptionHistory.setExcCode(RESTRICTED_ERROR_CODE);
			exceptionHistory.setExcMessage(RESTRICTED_ERROR_MESSAGE + ":"
					+ employee.getEmpId());
			addExceptionHistory(exceptionHistory);
			throw new MyServiceException("Restricted Access to Emp Id"
					+ employee.getEmpId());
		}
		return true;
	}

	@Override
	public boolean addExceptionHistory(ExceptionHistory exceptionHistory)
			throws Exception {
		exceptionHistoryDao.save(exceptionHistory);
		return false;
	}

	/**Helper method for checking employee record update request is within 24 hour
	 * @param empId
	 * @return
	 * @throws Exception
	 */
	private boolean isWithin24HourRequest(int empId) throws Exception {
		boolean result = false;
		logger.info("empId" + empId);

		Employee emp = employeeDao.findOne(Integer.valueOf(empId));
		if (emp != null) {
			logger.info("Emp Name" + emp.getEmpName() + "Emp MTYDate"
					+ emp.getMdyTS());

			Timestamp mdtTs = emp.getMdyTS();

			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss.S");

			Date oldTime = format.parse(mdtTs.toString());
			Date newTime = new Date();
			format.format(newTime);
			// in milliseconds
			long diff = newTime.getTime() - oldTime.getTime();
			long diffHours = diff / (60 * 60 * 1000);
			logger.info("TimeDiffernece" + diffHours);
			if (diffHours <= 24) {
				result = true;
			}

		}
		return result;
	}

}
