package com.spring.healthease.util;

import org.springframework.stereotype.Component;

import com.spring.healthease.entity.Review;
import com.spring.healthease.model.ReviewOutputModel;

@Component
public class MapRowReview {

	public ReviewOutputModel mapToReviewOutputModel(Review review) {
		
		ReviewOutputModel reviewOutputModel = new ReviewOutputModel();
		reviewOutputModel.setDoctorName(review.getDoc().getName());
		reviewOutputModel.setPatientName(review.getPatient().getName());
		reviewOutputModel.setReview(review.getComments());
	
		return reviewOutputModel;
		
	}
}
