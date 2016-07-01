package com.shitot.model;

import java.util.*;
import javax.persistence.*;

@Entity(name = "problems")
public class ProblemDAO extends NamedDAO {
	private String description;

	@ManyToMany
	List<SymptomDAO> symptoms;

	public ProblemDAO() {
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
