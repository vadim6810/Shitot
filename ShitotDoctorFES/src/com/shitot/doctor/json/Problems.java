package com.shitot.doctor.json;

import java.util.*;

public class Problems {

    private int id;

    private String problemName;
    private String description;
    
    List<Symptoms> symptoms;
   

    public Problems() { }
    

	public Problems(String nameProblem, String description, List<Symptoms> symptoms) {
		this.problemName = nameProblem;
		this.description = description;
		this.symptoms = symptoms;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNameProblem() {
		return problemName;
	}


	public void setNameProblem(String nameProblem) {
		this.problemName = nameProblem;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<Symptoms> getSymptoms() {
		return symptoms;
	}


	public void setSymptoms(List<Symptoms> symptoms) {
		this.symptoms = symptoms;
	}


	@Override
	public String toString() {
		return "Problems [id=" + id + ", nameProblem=" + problemName + ", description=" + description + ", symptoms="
				+ symptoms + "]";
	}




    


}
