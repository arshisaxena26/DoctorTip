package com.app.dto;

public class LikeDislikeDTO {

	private boolean dislike;
	private boolean like;
	private int docId;
	private int patId;

	public LikeDislikeDTO() {
		// Default Constructor
	}

	public LikeDislikeDTO(boolean dislike, boolean like, int docId, int patId) {
		this.dislike = dislike;
		this.like = like;
		this.docId = docId;
		this.patId = patId;
	}

	public boolean isDislike() {
		return dislike;
	}

	public void setDislike(boolean dislike) {
		this.dislike = dislike;
	}

	public boolean isLike() {
		return like;
	}

	public void setLike(boolean like) {
		this.like = like;
	}

	public int getDocId() {
		return docId;
	}

	public void setDocId(int docId) {
		this.docId = docId;
	}

	public int getPatId() {
		return patId;
	}

	public void setPatId(int patId) {
		this.patId = patId;
	}
}
