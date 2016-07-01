package com.shitot.to;

public class Symptom {

	int id;

	private String name;
	public Symptom() {
	}

	public Symptom(int id) {
		this.id = id;
	}

	public Symptom(String name) {
		this.name = name;
	}

	public Symptom(int id, String name) {
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return "Symptoms [id=" + id + ", name=" + name + "]";
	}
}
