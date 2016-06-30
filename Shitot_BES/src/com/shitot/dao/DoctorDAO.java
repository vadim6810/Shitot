package com.shitot.dao;

import java.util.*;
import javax.persistence.*;

@Entity(name = "doctors")
public class DoctorDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doctor_id")
	private int id;

	private String address;
	private String certification;
	private String comments;
	private String name;
	private String email;
	private String expert;
	private String lectors;
	private String otherSpecialty;
	private String password;
	private String preferential;
	private String specialty;
	private String targetAudience;
	private int targetFromAge;
	private int targetGender;// 1 - Male, 2 - Female, 0 - Both
	private int targetToAge;
	private String telHouse;
	private String telNumber;

	@OneToMany(mappedBy = "doctor")
	List<ClinicDAO> clinics;
	
	public DoctorDAO() {
	}

	public String getAddress() {
		return address;
	}

	public String getCertification() {
		return certification;
	}

	public List<ClinicDAO> getClinics() {
		return clinics;
	}

	public String getComments() {
		return comments;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getExpert() {
		return expert;
	}

	public int getId() {
		return id;
	}

	public String getLectors() {
		return lectors;
	}

	public String getOtherSpecialty() {
		return otherSpecialty;
	}

	public String getPassword() {
		return password;
	}

	public String getPreferential() {
		return preferential;
	}

	public String getSpecialty() {
		return specialty;
	}

	public String getTargetAudience() {
		return targetAudience;
	}

	public int getTargetFromAge() {
		return targetFromAge;
	}

	public int getTargetGender() {
		return targetGender;
	}

	public int getTargetToAge() {
		return targetToAge;
	}

	public String getTelHouse() {
		return telHouse;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public void setClinics(List<ClinicDAO> clinics) {
		this.clinics = clinics;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setExpert(String expert) {
		this.expert = expert;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLectors(String lectors) {
		this.lectors = lectors;
	}

	public void setOtherSpecialty(String otherSpecialty) {
		this.otherSpecialty = otherSpecialty;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPreferential(String preferential) {
		this.preferential = preferential;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public void setTargetAudience(String targetAudience) {
		this.targetAudience = targetAudience;
	}

	public void setTargetFromAge(int targetFromAge) {
		this.targetFromAge = targetFromAge;
	}

	public void setTargetGender(int targetGender) {
		this.targetGender = targetGender;
	}

	public void setTargetToAge(int targetToAge) {
		this.targetToAge = targetToAge;
	}

	public void setTelHouse(String telHouse) {
		this.telHouse = telHouse;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}
}
