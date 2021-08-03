package com.app.dto;

public class ShowCommentDTO {

	private Integer commentId;

	private String commentContent;

	private String commentReply;

	private boolean isLiked;

	private String patientName;
	private byte[] image;

	public ShowCommentDTO() {
		// Default Constructor
	}

	public ShowCommentDTO(Integer commentId, String commentContent, String commentReply, boolean isLiked,
			String patientName, byte[] image) {
		super();
		this.commentId = commentId;
		this.commentContent = commentContent;
		this.commentReply = commentReply;
		this.isLiked = isLiked;
		this.patientName = patientName;
		this.image = image;
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

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
