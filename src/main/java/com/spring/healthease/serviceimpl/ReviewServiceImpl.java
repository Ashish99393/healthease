package com.spring.healthease.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.healthease.entity.Doctor;
import com.spring.healthease.entity.Patient;
import com.spring.healthease.entity.Review;
import com.spring.healthease.exception.DoctorIdNotFoundException;
import com.spring.healthease.exception.PatientNotFoundException;
import com.spring.healthease.exception.SpecializationNotFoundException;
import com.spring.healthease.model.ReviewInputModel;
import com.spring.healthease.model.ReviewOutputModel;
import com.spring.healthease.repository.DoctorRepo;
import com.spring.healthease.repository.PatientRepo;
import com.spring.healthease.repository.ReviewRepo;
import com.spring.healthease.util.MapRowReview;

@Service
public class ReviewServiceImpl {

	@Autowired
	ReviewRepo reviewRepo;
	@Autowired
	DoctorServiceImpl doctorService;
	@Autowired
	PatientServiceImpl patientService;
	@Autowired
	PatientRepo patRepo;
	@Autowired
	DoctorRepo docRepo;
	
	public ReviewOutputModel addReviews(ReviewInputModel reviewModel) throws SpecializationNotFoundException, PatientNotFoundException {
		Doctor doctor=docRepo.findById(reviewModel.getDocId()).orElse(null);
		Patient patient=patRepo.findById(reviewModel.getPatId()).orElse(null);
		
		if(patient!=null) {
			Review review = new Review();
			review.setComments(reviewModel.getComments());
			review.setDoc(doctor);
			review.setPatient(patient);
			review= reviewRepo.save(review);
			
			ReviewOutputModel reviewOutputModel = new MapRowReview().mapToReviewOutputModel(review);
			
			return reviewOutputModel;
		}
		else {
			throw new PatientNotFoundException("Patient not found "); 
		}
		
		
	}
	
	public List<ReviewOutputModel> getAllReviews(Integer docId)throws DoctorIdNotFoundException{
		List<Review> reviewList = reviewRepo.findBydocId(docId);
		List<ReviewOutputModel> reviews= new ArrayList<>();
		if(!reviewList.isEmpty())
		{
			for(Review review: reviewList) {
				ReviewOutputModel reviewOutputModel = new MapRowReview().mapToReviewOutputModel(review);
				reviews.add(reviewOutputModel);
			}
				return reviews;
		}
		else
			throw new DoctorIdNotFoundException("No Review found!");
	}
	public void deleteReview(Integer reviewId) 
	{
		Review review=reviewRepo.findById(reviewId).orElse(null);
		if(review!=null) {
			reviewRepo.delete(review);
		}
	
		
	}
}
