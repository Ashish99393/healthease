package com.spring.healthease.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.healthease.entity.Review;
import com.spring.healthease.exception.DoctorIdNotFoundException;
import com.spring.healthease.exception.PatientNotFoundException;
import com.spring.healthease.exception.SpecializationNotFoundException;
import com.spring.healthease.model.ReviewInputModel;



@Service
public interface ReviewService 
{
	
	public Review addReviews(ReviewInputModel reviewModel) throws SpecializationNotFoundException, PatientNotFoundException;
	
	public List<Review> getAllReviews(Integer docId)throws DoctorIdNotFoundException;
	public void deleteReview(Integer reviewId);
	

}
