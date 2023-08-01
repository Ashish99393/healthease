package com.spring.healthease.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.healthease.entity.Doctor;
import com.spring.healthease.exception.DoctorNotAvaliableException;
import com.spring.healthease.exception.SpecializationNotFoundException;
import com.spring.healthease.model.AppointmentOutputModel;
import com.spring.healthease.serviceimpl.DoctorServiceImpl;
import com.spring.healthease.services.DoctorService;
@CrossOrigin("*")
@RestController
public class DoctorController {
	
	@Autowired
	DoctorServiceImpl docService;
	Logger logger=LoggerFactory.getLogger(AppointmentController.class);
	
	@GetMapping("/doctor/{id}")
	public ResponseEntity<Doctor> getDocById(@PathVariable("id") Integer id){
		logger.info("getDocById() method started");
		Doctor doc =docService.getDocById(id);
		logger.info("Doctor={}",doc);
		logger.info("searchSpecialization() method ended");
		return new ResponseEntity<Doctor>(doc, HttpStatus.OK);
	}
	
	@GetMapping("/searchspecialization/{specialization}")
	public ResponseEntity<List<Doctor>> searchSpecialization(@PathVariable("specialization") String speciality)throws SpecializationNotFoundException {
		logger.info("searchSpecialization() method started");
		List<Doctor> doctors =docService.searchSpecialization(speciality);
		logger.info("Doctors list={}",doctors);
		logger.info("searchSpecialization() method ended");
		return new ResponseEntity<List<Doctor>>(doctors, HttpStatus.OK);
		
	}
	
	@GetMapping("/alldoctors")
	public ResponseEntity<List<Doctor>> getAllDoctors () {
		logger.info("searchSpecialization() method started");
		List<Doctor> doctors =docService.getAllDoctors();;
		logger.info("Doctors list={}",doctors);
		logger.info("searchSpecialization() method ended");
		return new ResponseEntity<List<Doctor>>(doctors, HttpStatus.OK);
	}
	

}
