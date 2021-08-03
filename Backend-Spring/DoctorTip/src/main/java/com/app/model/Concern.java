package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "concern")
public class Concern {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "concern_id")
	private Integer concernId;

	@NotNull
	@Column(name = "concern_comment", length = 100)
	private String concernComment;

	@Column(name = "is_warned")
	private boolean isWarned = false;
    
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "appointment_id")
	private Appointment appointment;

	public Concern() {
		// Default Constructor
	}
     
	
	public Concern( String concernComment, Appointment appointment) {
		super();
		this.concernComment = concernComment;
		this.appointment = appointment;
	}


	public Concern(String concernComment, boolean isWarned) {

		this.concernComment = concernComment;
		this.isWarned = isWarned;
	}

	public Integer getConcernId() {
		return concernId;
	}

	public void setConcernId(Integer concernId) {
		this.concernId = concernId;
	}

	public String getConcernComment() {
		return concernComment;
	}

	public void setConcernComment(String concernComment) {
		this.concernComment = concernComment;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public boolean isWarned() {
		return isWarned;
	}

	public void setWarned(boolean isWarned) {
		this.isWarned = isWarned;
	}

	@Override
	public String toString() {
		return "Concern [concernId=" + concernId + ", concernComment=" + concernComment + "]";
	}
}
