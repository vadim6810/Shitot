package com.shitot.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity(name = "treatment")
public class TreatmentDAO extends BaseDAO {

	private int payment;
	private String checkNumber;
	boolean isAlternativeDoctor;
	// private String doctor;

	@Temporal(TemporalType.DATE)
	private Date applyDate;
	@Temporal(TemporalType.DATE)
	private Date meetingDate;
	@Temporal(TemporalType.DATE)
	private Date paymentDate;
	private String userName;
	private String infSourse;
	private String description;

	@OneToMany
	List<ProblemDAO> problems;
	@OneToOne
	DoctorDAO doctor;
	@OneToOne
	DoctorDAO alternativeDoctor;

	public TreatmentDAO() {
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public Date getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public List<ProblemDAO> getProblems() {
		return problems;
	}

	public void setProblems(List<ProblemDAO> problems) {
		this.problems = problems;
	}

	public DoctorDAO getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorDAO doctor) {
		this.doctor = doctor;
	}

	public boolean isAlternativeDoctor() {
		return isAlternativeDoctor;
	}

	public void setIsAlternativeDoctor(boolean isAlternativeDoctor) {
		this.isAlternativeDoctor = isAlternativeDoctor;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getInfSourse() {
		return infSourse;
	}

	public void setInfSourse(String infSourse) {
		this.infSourse = infSourse;
	}

	public DoctorDAO getAlternativeDoctor() {
		return alternativeDoctor;
	}

	public void setAlternativeDoctor(DoctorDAO alternativeDoctor) {
		this.alternativeDoctor = alternativeDoctor;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
