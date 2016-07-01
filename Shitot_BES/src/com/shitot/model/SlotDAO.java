package com.shitot.model;

import java.util.*;
import javax.persistence.*;

@Entity(name = "slots")
public class SlotDAO extends BaseDAO {

	// private int dayOfWeek;
	@Temporal(TemporalType.DATE)
	private Date date;

	@OneToMany
	List<IntervalDAO> intervals;

	public SlotDAO() {
	}

	/*
	 * public int getDayOfWeek() { return dayOfWeek; }
	 * 
	 * public void setDayOfWeek(int dayOfWeek) { this.dayOfWeek = dayOfWeek; }
	 */
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<IntervalDAO> getIntervals() {
		return intervals;
	}

	public void setIntervals(List<IntervalDAO> intervals) {
		this.intervals = intervals;
	}
}
