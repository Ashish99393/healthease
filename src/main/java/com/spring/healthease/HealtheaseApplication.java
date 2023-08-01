package com.spring.healthease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HealtheaseApplication {
	
//	private static PatientService patService;
//	private static DoctorService docService;
//	private static ReviewService reviewService;
	
	public static void main(String[] args) {
		SpringApplication.run(HealtheaseApplication.class, args);
//		patService=ctx.getBean(PatientService.class);
//		docService=ctx.getBean(DoctorService.class);
//		reviewService=ctx.getBean(ReviewService.class);
//		addDoctor();
//		addPatient();
//		searchBySpecialization();
//		addReview();
//		searchByPatientId();
//		searchByDoctorId();
//		getAllReviewsOfAParticularDoctor();
//		AuthenticatePatient();
		}
	
//	private static void AuthenticatePatient() {
//		boolean isUser;
//		try {
//			isUser = patService.authenticateUser("Priya", "Anjali@healthease");
//			System.out.println(isUser);
//		} catch (PatientNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
//	private static void getAllReviewsOfAParticularDoctor(){
//		try {
//			List<Review> reviewList = reviewService.getAllReviews(1);
//			for(Review d: reviewList)
//				System.out.println(d.getComments());
//			
//		} catch (DoctorIdNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		
//	}
//	private static void addReview() {
//		
//		ReviewModel reviewModel=new ReviewModel();
//		Patient patient=new Patient();
//		Doctor doc=new Doctor();
//		reviewModel.setComments("Really a tough doctor, cured my heart aches");
//		try {
//			reviewService.addReviews(reviewModel,1,1);
//		} catch (SpecializationNotFoundException | PatientNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	public static void addPatient() {
//		Patient patient=new Patient();
//		patient.setName("Anjali");
//		patient.setPassword("Anjali@healthease");
//		patient.setAddress("kharda");
//		patient.setAge(23);
//		patient.setEmail("anjali@gmail.com");
//		
//		try {
//		patService.addPatients(patient);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	public static void addDoctor() {
//		Doctor doc=new Doctor();
//		doc.setName("Debasish ");	
//		doc.setSpecialization("Cardiologyist");
//		doc.setAvailableDays("Monday,Tuesday,Thursday");
//		docService.addDoctors(doc);
//		
//	}
//	public static void searchBySpecialization() {
//		try {
//			List<Doctor> docList =docService.searchSpecialization("cardiologyist");
//			for(Doctor d: docList)
//			System.out.println(d);
//		} catch (SpecializationNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	public static void searchByPatientId() {
//		
//			try {
//				Patient patient = patService.getByPatientId(10);
//			} catch (PatientNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		
//	}
//	
//	public static void searchByDoctorId() {
//		
//		try {
//			Doctor doctor = docService.getByDocId(10);
//		} catch (SpecializationNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	
}

