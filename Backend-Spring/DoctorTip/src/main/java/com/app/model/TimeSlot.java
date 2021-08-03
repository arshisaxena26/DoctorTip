package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "timeslot")
public class TimeSlot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "time_slot_id")
	private Integer timeSlotId;

	@NotNull
	@Column(name = "time_slot", length = 10)
	private String slot;

	public TimeSlot() {
		// Default Constructor
	}

	public TimeSlot(int timeSlotId, String slot) {
		this.timeSlotId = timeSlotId;
		this.slot = slot;
	}

	public Integer getTimeSlotId() {
		return timeSlotId;
	}

	public void setTimeSlotId(Integer timeSlotId) {
		this.timeSlotId = timeSlotId;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	@Override
	public String toString() {
		return "TimeSlot [timeSlotId=" + timeSlotId + ", slot=" + slot + "]";
	}
}
