package com.shitot.dao;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "patients")
public class PatientDAO {

    @Id
    @Column(name = "patient_id")
    private int id;

    private String name;
    @Column(unique=true)
    private String telNumber;
    private int age;
    private String description;

    @OneToMany
    List<TreatmentDAO> treatments;

	public PatientDAO() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
