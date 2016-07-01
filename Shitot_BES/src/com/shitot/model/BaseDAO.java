package com.shitot.model;

import javax.persistence.*;

@MappedSuperclass
public class BaseDAO {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;

	public BaseDAO() {
	}

	public BaseDAO(Integer id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public boolean isNew() {
		return id==null;
	}

	@Override
	public int hashCode() {
		return (id == null) ? 0 : id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseDAO other = (BaseDAO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
