package com.spring.healthease.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.healthease.entity.Review;
import com.spring.healthease.exception.DoctorIdNotFoundException;
import com.spring.healthease.exception.PatientNotFoundException;
import com.spring.healthease.exception.SpecializationNotFoundException;
import com.spring.healthease.model.ReviewInputModel;
import com.spring.healthease.model.ReviewOutputModel;
import com.spring.healthease.serviceimpl.ReviewServiceImpl;
@CrossOrigin("*")
@RestController
public class ReviewController {
	Logger logger=LoggerFactory.getLogger(AppointmentController.class);
	@Autowired
	ReviewServiceImpl reviewService;
	@PostMapping("/addreview")
	public ResponseEntity<ReviewOutputModel> addReviews(@RequestBody ReviewInputModel reviewModel) throws SpecializationNotFoundException, PatientNotFoundException{
		logger.info("addReviews() method started");
		ReviewOutputModel reviewOutputModel=reviewService.addReviews(reviewModel);
		logger.info("Review added={}", reviewOutputModel);
		logger.info("addReviews() method ended");
		return new ResponseEntity<ReviewOutputModel>(reviewOutputModel, HttpStatus.OK);
	}
	
	@GetMapping("/getallreview/{docid}")
	public ResponseEntity<List<ReviewOutputModel>> getAllReviews(@PathVariable("docid") Integer docId) throws DoctorIdNotFoundException{
		logger.info("addReviews() method started");
		List<ReviewOutputModel> reviews =reviewService.getAllReviews(docId);
		logger.info("Review list={}", reviews);
		logger.info("getAllReviews() method ended");
		return new ResponseEntity<List<ReviewOutputModel>>(reviews,HttpStatus.OK);
	}
	@DeleteMapping("/deletereview/{review}")
	public void deleteReview(@PathVariable("review") Integer reviewId) {
		logger.info("deleteReview() method started");
		reviewService.deleteReview(reviewId);
		logger.info("addReviews() method ended");		 
		
	}

}
