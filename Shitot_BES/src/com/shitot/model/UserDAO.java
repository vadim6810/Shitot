package com.shitot.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name="users")
@Table(uniqueConstraints={@UniqueConstraint(columnNames="name",name="usr_unq_nme_idx")})
public class UserDAO extends NamedDAO{

    private String password;

    public UserDAO() {}

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }
}
