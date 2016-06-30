package com.shitot.dao;

import java.util.*;
import javax.persistence.*;

import com.sun.istack.internal.NotNull;

@Entity(name = "problems")
public class ProblemDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "problems_id")
	private int id;

	@NotNull
	private String name;
	private String description;

	@ManyToMany
	List<SymptomDAO> symptoms;

	public ProblemDAO() {
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

	public List<SymptomDAO> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(List<SymptomDAO> symptoms) {
		this.symptoms = symptoms;
	}
}
