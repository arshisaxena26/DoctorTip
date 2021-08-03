package com.app.dto;

public class CommentDTO {

	private String commentContent;
	private Integer docId;
	private Integer userId;

	public CommentDTO() {
		// Default Constructor
	}

	public CommentDTO(String commentContent, Integer docId, Integer userId) {
		super();
		this.commentContent = commentContent;
		this.docId = docId;
		this.userId = userId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Integer getDocId() {
		return docId;
	}

	public void setDocId(Integer docId) {
		this.docId = docId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
