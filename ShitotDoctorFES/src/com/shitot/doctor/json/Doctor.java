package com.shitot.doctor.json;

import java.util.*;

public class Doctor {

	private int id;

	private String address;
	private String certification;
	private String comments;
	private String doctorName;
	private String email;
	private String expert;
	private String lectors;
	private String otherSpecialty;
	private String password;
	private String preferential;
	private String specialty;
	private int targetFromAge;
	private int targetGender;// 1 - Male, 2 - Female, 0 - Both
	private int targetToAge;
	private String telHouse;
	private String telNumber;

	List<Clinic> clinics;

	public Doctor() {
	}

	public Doctor(String address, String certification, String comments, String doctorName, String email, String expert,
			String lectors, String otherSpecialty, String password, String preferential, String specialty,
			int targetFromAge, int targetGender, int targetToAge, String telHouse, String telNumber) {
		this.address = address;
		this.certification = certification;
		this.comments = comments;
		this.doctorName = doctorName;
		this.email = email;
		this.expert = expert;
		this.lectors = lectors;
		this.otherSpecialty = otherSpecialty;
		this.password = password;
		this.preferential = preferential;
		this.specialty = specialty;
		this.targetFromAge = targetFromAge;
		this.targetGender = targetGender;
		this.targetToAge = targetToAge;
		this.telHouse = telHouse;
		this.telNumber = telNumber;
	}

	public String getAddress() {
		return address;
	}

	public String getCertification() {
		return certification;
	}

	public List<Clinic> getClinics() {
		return clinics;
	}

	public String getComments() {
		return comments;
	}

	public String getDoctorName() {
		return doctorName;
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

	public void setClinics(List<Clinic> clinics) {
		this.clinics = clinics;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
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

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", doctorName=" + doctorName + ", password=" + password + ", telNumber=" + telNumber
				+ ", telHouse=" + telHouse + ", email=" + email + ", address=" + address + ", specialty=" + specialty
				+ ", targetFromAge=" + targetFromAge + ", targetToAge=" + targetToAge + ", targetGender=" + targetGender
				+ ", otherSpecialty=" + otherSpecialty + ", preferential=" + preferential + ", expert=" + expert
				+ ", certification=" + certification + ", lectors=" + lectors + ", comments=" + comments + ", clinics="
				+ clinics + "]";
	}

}
