package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.model.Likes;

public interface LikesRepository extends JpaRepository<Likes, Integer> {

	@Query("select l from Likes l where l.doctor.doctorId = :doctor_id and l.isLiked=true")
	public List<Likes> getDoctorLikes(@Param("doctor_id") Integer doctorId);

	// Query to extract like row for a specific doctor and patient
	@Query(value = "select * from likes_dislikes where doctor_id=:docId and patient_id=:patId", nativeQuery = true)
	Likes findByDocIdandPatId(@Param("docId") Integer docId, @Param("patId") Integer patId);

	// Query to count Likes
	@Query(value = "select count(*) from likes_dislikes where doctor_id=:docId and is_liked = true", nativeQuery = true)
	int countLikes(@Param("docId") Integer docId);

	// Query to count dislikes
	@Query(value = "select count(*) from likes_dislikes where doctor_id=:docId and is_disliked = true", nativeQuery = true)
	int countDisikes(@Param("docId") Integer docId);
}
