package com.spring.healthease.servicetest;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.healthease.entity.Doctor;
import com.spring.healthease.exception.SpecializationNotFoundException;
import com.spring.healthease.repository.DoctorRepo;
import com.spring.healthease.serviceimpl.DoctorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class DoctorServiceTest {

	@Mock
	private DoctorRepo doctorRepo;

	@InjectMocks
	private DoctorServiceImpl doctorService;

	@Test
	public void testGetAllDoctors() {
		// Create a list of doctors
		List<Doctor> doctorList = new ArrayList<>();

		Doctor doctor1 = new Doctor();
		doctor1.setDocId(1);
		doctor1.setAvailableDays("Monday");
		doctor1.setSpecialization("Cardiology");
		doctor1.setName("Dr. John");
		doctorList.add(doctor1);

		Doctor doctor2 = new Doctor();
		doctor2.setDocId(2);
		doctor2.setAvailableDays("Tuesday");
		doctor2.setSpecialization("Dermatology");
		doctor2.setName("Dr. Emily");
		doctorList.add(doctor2);

		// Mock the behavior of the doctorRepo.findAll() method
		when(doctorRepo.findAll()).thenReturn(doctorList);

		// Call the method to be tested
		List<Doctor> result = doctorService.getAllDoctors();

		// Verify the result
		assertEquals(doctorList.size(), result.size());
		assertEquals(doctorList.get(0).getName(), result.get(0).getName());
		assertEquals(doctorList.get(1).getSpecialization(), result.get(1).getSpecialization());
		// Add more assertions if needed
	}

	@Test
	public void testSearchSpecialization() throws SpecializationNotFoundException {
		String speciality = "Cardiology";
		Doctor doc1 = new Doctor();
		doc1.setDocId(1);
		doc1.setAvailableDays("Monday");
		doc1.setName("Dr. Smith");
		doc1.setSpecialization("Cardiology");
		Doctor doc2 = new Doctor();
		doc2.setDocId(2);
		doc2.setAvailableDays("Tuesday");
		doc2.setName("Dr. Jhonson");
		doc2.setSpecialization("Cardiology");
		List<Doctor> doctors = new ArrayList<>();
		doctors.add(doc1);
		doctors.add(doc2);
		when(doctorRepo.findBySpecialization("Cardiology")).thenReturn(doctors);
		List<Doctor> result = doctorService.searchSpecialization(speciality);
		assertEquals(doctors, result);

	}
}
