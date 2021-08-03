package com.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Integer commentId;

	@NotNull
	@Column(name = "comment_content", length = 1000)
	private String commentContent;

	@Nullable
	@Column(name = "comment_reply", length = 1000)
	private String commentReply;

	@Column(name = "is_liked")
	private boolean isLiked = false;
    
	@JsonIgnore
	// Unidirectional Mapping
	@ManyToOne(targetEntity = Doctor.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;
    
	@JsonIgnore
	// Unidirectional Mapping
	@ManyToOne(targetEntity = Patient.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id")
	private Patient patient;

	public Comment() {
		// Default Constructor
	}

	public Comment(String commentContent, String commentReply, boolean isLiked) {
		this.commentContent = commentContent;
		this.commentReply = commentReply;
		this.isLiked = isLiked;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentReply() {
		return commentReply;
	}

	public void setCommentReply(String commentReply) {
		this.commentReply = commentReply;
	}

	public boolean isLiked() {
		return isLiked;
	}

	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", commentContent=" + commentContent + ", commentReply="
				+ commentReply + ", isLiked=" + isLiked + "]";
	}
}
