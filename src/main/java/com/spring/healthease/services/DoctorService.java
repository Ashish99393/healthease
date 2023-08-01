package com.spring.healthease.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.healthease.entity.Doctor;
import com.spring.healthease.exception.SpecializationNotFoundException;

@Service
public interface DoctorService {

	
	public List<Doctor> searchSpecialization(String speciality)throws SpecializationNotFoundException ;
	public List<Doctor> getAllDoctors();
	public Doctor getDocById(Integer id);
	
}
