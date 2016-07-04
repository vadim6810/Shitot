package com.shitot.model;

import javax.persistence.*;

@Entity(name = "intervals")
public class IntervalDAO extends BaseDAO {

	private String hours;
	
	@OneToOne
	TreatmentDAO treatment;

	public IntervalDAO() {
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public TreatmentDAO getTreatment() {
		return treatment;
	}

	public void setTreatment(TreatmentDAO treatment) {
		this.treatment = treatment;
	}
}
