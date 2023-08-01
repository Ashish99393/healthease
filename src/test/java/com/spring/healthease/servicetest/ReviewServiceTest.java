package com.spring.healthease.servicetest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
import com.spring.healthease.serviceimpl.ReviewServiceImpl;
import com.spring.healthease.services.ReviewService;
import com.spring.healthease.util.MapRowReview;

@ExtendWith(SpringExtension.class)
public class ReviewServiceTest {
    
    @Mock
    private DoctorRepo docRepo;
    
    @Mock
    private PatientRepo patRepo;
    
    @Mock
    private ReviewRepo reviewRepo;
    
    @InjectMocks
    private ReviewServiceImpl reviewService;
    
    @Mock
    MapRowReview mapRowReview;
    
    @Test
    public void testAddReviews() throws SpecializationNotFoundException, PatientNotFoundException {
        // Mock data
        ReviewInputModel reviewModel = new ReviewInputModel();
        reviewModel.setComments("Great doctor!");
        
        Integer doctorId = 1;
        Integer patientId = 1;
        
        Patient patient = new Patient();
        patient.setPatientId(patientId);
        
        Doctor doctor = new Doctor();
        doctor.setDocId(doctorId);
        
        Review savedReview = new Review();
        savedReview.setComments(reviewModel.getComments());
        savedReview.setDoc(doctor);
        savedReview.setPatient(patient);
        
        // Mock repository methods
        Mockito.when(docRepo.findById(doctorId)).thenReturn(Optional.of(doctor));
        Mockito.when(patRepo.findById(patientId)).thenReturn(Optional.of(patient));
        Mockito.when(reviewRepo.save(Mockito.any(Review.class))).thenReturn(savedReview);
        
        // Call the method
//        ReviewOutputModel result = reviewService.addReviews(reviewModel, doctorId, patientId);
        
        
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals(savedReview.getComments(), result.getReview());
    }
    
    @Test
    public void testGetAllReviews() throws DoctorIdNotFoundException {
        // Arrange
        Integer doctorId = 1;
        List<Review> reviewList = new ArrayList<>();
        Review review1 = new Review();
        review1.setComments("Good doctor");

     

        // Create a Doctor object and set it as the doc
        Doctor doctor = new Doctor();
        doctor.setName("Dr. John Doe");
        review1.setDoc(doctor);

     

        // Create a Patient object and set it as the patient
        Patient patient = new Patient();
        patient.setName("Alice");
        review1.setPatient(patient);

     

        reviewList.add(review1);
        when(reviewRepo.findBydocId(doctorId)).thenReturn(reviewList);

     

        ReviewOutputModel reviewOutputModel1 = new ReviewOutputModel();
        reviewOutputModel1.setDoctorName("Dr. John Doe");
        reviewOutputModel1.setPatientName("Alice");
        reviewOutputModel1.setReview("Good doctor");
        when(mapRowReview.mapToReviewOutputModel(review1)).thenReturn(reviewOutputModel1);

     

        // Act
        List<ReviewOutputModel> result = reviewService.getAllReviews(doctorId);

     

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        ReviewOutputModel resultModel = result.get(0);
        assertEquals("Dr. John Doe", resultModel.getDoctorName());
        assertEquals("Alice", resultModel.getPatientName());
        assertEquals("Good doctor", resultModel.getReview());

  
    }

    
    
    @Test
    public void testDeleteReview_ReviewExists() {
        // Arrange
        Integer reviewId = 1;

        Review review = new Review();
        review.setReviewId(reviewId);
        review.setComments("Great doctor");

        Mockito.when(reviewRepo.findById(reviewId)).thenReturn(java.util.Optional.of(review));

        // Act
        reviewService.deleteReview(reviewId);

        // Assert
        Mockito.verify(reviewRepo).delete(review);
    }

    @Test
    public void testDeleteReview_ReviewDoesNotExist() {
        // Arrange
        Integer reviewId = 1;

        Mockito.when(reviewRepo.findById(reviewId)).thenReturn(java.util.Optional.empty());

        // Act
        reviewService.deleteReview(reviewId);

        // Assert
        Mockito.verify(reviewRepo, Mockito.never()).delete(Mockito.any());
    }
	    
}

