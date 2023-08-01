package com.spring.healthease.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.healthease.entity.Review;

public interface ReviewRepo extends JpaRepository<Review, Integer> {
	
	@Query("select r from Review r where r.doc.docId=?1")
	List<Review> findBydocId(Integer docId);

}
