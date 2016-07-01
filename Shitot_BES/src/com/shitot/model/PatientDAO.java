package com.shitot.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity(name = "patients")
public class PatientDAO extends NamedDAO {

	private String telNumber;
	private int age;
	private String description;

	@OneToMany
	List<TreatmentDAO> treatments;

	public PatientDAO() {
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<TreatmentDAO> getTreatments() {
		return treatments;
	}

	public void setTreatments(List<TreatmentDAO> treatments) {
		this.treatments = treatments;
	}

}
