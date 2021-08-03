package com.app.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.model.GenericUser;
import com.app.model.UserActivity;

public interface UserActivityRepository extends JpaRepository<UserActivity, Integer> {

	@Query(value = "select activity_time from user_activity where activity_content = 'LogIn' and user_id = :id order by activity_id desc limit 1", nativeQuery = true)
	public LocalDateTime getLoginDate(@Param("id") int userId);

	@Query(value = "select * from user_activity where activity_content = 'LogIn' and user_id = :id order by activity_id desc limit 1", nativeQuery = true)
	public UserActivity getUserActivity(@Param("id") int userId);

	public List<UserActivity> findByUser(GenericUser user);

	@Query(value = "select count(*) from user_activity where activity_content = 'LogIn' and user_id !=1 group by user_id having count(*) > 1;", nativeQuery = true)
	public List<Integer> getReturningUserCount();

}
