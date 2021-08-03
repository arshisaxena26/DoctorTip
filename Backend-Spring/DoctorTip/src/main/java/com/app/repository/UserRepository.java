package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.GenericUser;

public interface UserRepository extends JpaRepository<GenericUser, Integer> {
	GenericUser findByUserEmail(String username);

	Optional<GenericUser> findByUserName(String userName);

	@Query("select u from GenericUser u where u.userName != 'admin'")
	List<GenericUser> getUsers();
}