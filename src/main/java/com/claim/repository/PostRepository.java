package com.claim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.claim.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	@Query("Select P from Post P where P.email IN :postList ORDER BY createdDate DESC")
	public List<Post> findPost(@Param("postList") String[] postList);
	
}
