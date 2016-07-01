package com.shitot.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class NamedDAO extends BaseDAO {
	
	@NotNull
	protected String name;

	public NamedDAO() {
	}

	public NamedDAO(Integer id, String name) {
		super(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
