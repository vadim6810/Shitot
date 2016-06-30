package com.shitot.json;

import java.util.*;

public class Problem {

	private int id;

	private String name;
	private String description;

	List<Symptom> symptoms;

	public Problem() {
	}

	public Problem(String name, String description, List<Symptom> symptoms) {
		this.name = name;
		this.description = description;
		this.symptoms = symptoms;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Symptom> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(List<Symptom> symptoms) {
		this.symptoms = symptoms;
	}

	@Override
	public String toString() {
		return "Problems [id=" + id + ", name=" + name + ", description=" + description + ", symptoms="
				+ symptoms + "]";
	}

}
