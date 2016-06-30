package com.shitot.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name="users")
@Table(uniqueConstraints={@UniqueConstraint(columnNames="name",name="usr_unq_nme_idx")})
public class UserDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    
    private String name;
    private String password;

    public UserDAO() {}

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getId() {
	return id;
    }

}
