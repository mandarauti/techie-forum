package com.mansoft.practice.services.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author mandar_auti
 *
 */
@Entity
@Table(name = "employee", catalog = "testdb")
public class Employee implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3291154137196552982L;
	@Column(name="EMP_ID")
	private Integer empId;
	@Column(name="EMP_NAME")
	private String empName;
	@Column(name="EMP_JOININGDATE")
	private Timestamp empJoiningdate;
	@Column(name="EMP_DEPT")
	private String empDepartment;
	
	@Column(name="MDY_TS")
	private Timestamp mdyTS;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "EMP_ID", unique = true, nullable = false)
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	@Column(name = "EMP_NAME", nullable = false)
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	@Column(name = "EMP_JOININGDATE",  nullable = false)
	public Timestamp getEmpJoiningdate() {
		return empJoiningdate;
	}
	public void setEmpJoiningdate(Timestamp empJoiningdate) {
		this.empJoiningdate = empJoiningdate;
	}
	@Column(name = "EMP_DEPT",  nullable = false)
	public String	 getEmpDepartment() {
		return empDepartment;
	}
	public void setEmpDepartment(String empDepartment) {
		this.empDepartment = empDepartment;
	}
	@Column(name = "MDY_TS",  nullable = false)
	public Timestamp getMdyTS() {
		return mdyTS;
	}
	public void setMdyTS(Timestamp mdyTS) {
		this.mdyTS = mdyTS;
	}

		

	
	
}
	
	
