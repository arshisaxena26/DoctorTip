package com.app.model;

import java.time.LocalDateTime;

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

@Entity
@Table(name = "user_activity")
public class UserActivity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "activity_id")
	private Integer activityId;

	@NotNull(message = "Activity Content cannot be Null")
	@Column(name = "activity_content", length = 300)
	private String activityContent;

	@NotNull(message = "Activity Time cannot be Null")
	@Column(name = "activity_time")
	private LocalDateTime activityTime;

	// Unidirectional Mapping
	@ManyToOne(targetEntity = GenericUser.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private GenericUser user;

	public UserActivity() {
		// Default Constructor
	}

	public UserActivity(String activityContent, LocalDateTime activityTime, GenericUser user) {
		this.activityContent = activityContent;
		this.activityTime = activityTime;
		this.user = user;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getActivityContent() {
		return activityContent;
	}

	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}

	public LocalDateTime getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(LocalDateTime activityTime) {
		this.activityTime = activityTime;
	}

	public GenericUser getUser() {
		return user;
	}

	public void setUser(GenericUser user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserActivity [activityId=" + activityId + ", activityContent=" + activityContent + ", activityTime="
				+ activityTime + "]";
	}
}
