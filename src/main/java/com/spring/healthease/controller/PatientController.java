package com.spring.healthease.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.spring.healthease.entity.Patient;
import com.spring.healthease.exception.InvalidRegistrationException;
import com.spring.healthease.exception.PatientNotFoundException;
import com.spring.healthease.model.PatientInputModel;
import com.spring.healthease.model.PatientOutputModel;
import com.spring.healthease.serviceimpl.PatientServiceImpl;
@CrossOrigin("*")
@RestController
public class PatientController {

	Logger logger=LoggerFactory.getLogger(AppointmentController.class);
	@Autowired
	PatientServiceImpl patService;
	@PostMapping("/addpatient/")
	@CrossOrigin("*")
	public ResponseEntity<PatientOutputModel> addPatients(@RequestBody PatientInputModel patient) throws InvalidRegistrationException 
	{
		logger.info("addPatients() method started");
		PatientOutputModel patientOutputModel= patService.addPatients(patient);
		logger.info("Patient added={}", patientOutputModel);
		logger.info("addPatients() method ended");
		return new ResponseEntity<PatientOutputModel>(patientOutputModel,HttpStatus.OK); 
	}
	
	@GetMapping("/loginpatient/{username}")
	public ResponseEntity<PatientOutputModel> loginPatients(@PathVariable("username") String username, String password) throws PatientNotFoundException {
		logger.info("loginPatients() method started");
		PatientOutputModel patientOutputModel= patService.loginPatients(username, password);
		logger.info("Login={}", patientOutputModel);
		logger.info("loginPatients() method ended");
		return new ResponseEntity<PatientOutputModel>(patientOutputModel,HttpStatus.OK); 
		
	}
	
}
