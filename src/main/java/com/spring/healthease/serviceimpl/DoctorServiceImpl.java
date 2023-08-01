package com.spring.healthease.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.healthease.entity.Doctor;
import com.spring.healthease.exception.SpecializationNotFoundException;
import com.spring.healthease.repository.DoctorRepo;

@Service
public class DoctorServiceImpl {

	@Autowired
	DoctorRepo doctorRepo;
	
	
	public List<Doctor> searchSpecialization(String speciality)throws SpecializationNotFoundException {
	   
		 List<Doctor> docs =doctorRepo.findBySpecialization(speciality);
		   if(!docs.isEmpty())
			   return docs;
		   else {
		   throw new SpecializationNotFoundException("No doctor found with this specialization!");
	   }
	}
	
	public List<Doctor> getAllDoctors(){
		return doctorRepo.findAll();
	}
	public Doctor getDocById(Integer id) {
		Doctor doc= doctorRepo.findById(id).orElse(null);
		return doc;
		
	}
}
