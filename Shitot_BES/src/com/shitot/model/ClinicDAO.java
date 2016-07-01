package com.shitot.model;

import java.util.*;

import javax.persistence.*;

@Entity(name="clinics")
public class ClinicDAO extends BaseDAO{
	
	private String city;
	private String address;
	
	@ManyToOne
	DoctorDAO doctor;
	
	@OneToMany
	List<SlotDAO> slots;
	
	public ClinicDAO() {}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<SlotDAO> getSlots() {
		return slots;
	}

	public void setSlots(List<SlotDAO> slots) {
		this.slots = slots;
	}
	

	public DoctorDAO getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorDAO doctor) {
		this.doctor = doctor;
	}

	@Override
	public String toString() {
		return "ClinicDAO [id=" + id + ", city=" + city + ", address=" + address + ", doctor=" + doctor
				+ ", slots=" + slots + "]";
	}

	
	
	

}
