package com.mansoft.practice.services.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "exception_history", catalog = "testdb")
public class ExceptionHistory implements java.io.Serializable {

	@Column(name="EXC_ID")
	private Integer excId;
	@Column(name="EXC_CODE")
	private String excCode;
	@Column(name="EXC_MSG")
	private String excMessage;
	@Column(name="CRT_TS")
	private Timestamp crtTs;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "EXC_ID", unique = true, nullable = false)
	public Integer getExcId() {
		return excId;
	}
	public void setExcId(Integer excId) {
		this.excId = excId;
	}
	@Column(name = "EXC_CODE")
	public String getExcCode() {
		return excCode;
	}
	
	public void setExcCode(String excCode) {
		this.excCode = excCode;
	}
	@Column(name = "EXC_MSG")
	public String getExcMessage() {
		return excMessage;
	}
	public void setExcMessage(String excMessage) {
		this.excMessage = excMessage;
	}
	@Column(name = "CRT_TS", unique = true, nullable = false)
	public Timestamp getCrtTs() {
		return crtTs;
	}
	public void setCrtTs(Timestamp crtTs) {
		this.crtTs = crtTs;
	}
	
	
	
}
	
	
