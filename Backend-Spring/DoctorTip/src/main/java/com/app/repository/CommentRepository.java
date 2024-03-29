package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

	@Query(value = "select * from comment where doctor_id=:docId", nativeQuery = true)
	List<Comment> findByDoctor(int docId);

}
