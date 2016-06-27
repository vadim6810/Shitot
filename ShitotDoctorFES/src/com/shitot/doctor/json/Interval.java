package com.shitot.doctor.json;



public class Interval {
	
	private int id;
	
	private String hours;
	
	Treatment treatment;

	public Interval() {	}
	
	public Interval(String hours) {
		this.hours = hours;
	}





	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public Treatment getTreatment() {
		return treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

	@Override
	public String toString() {
		return "Interval [id=" + id + ", hours=" + hours + ", treatment=" + treatment + "]";
	}

	
	

}
