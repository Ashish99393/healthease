package com.spring.healthease.services;


import org.springframework.stereotype.Service;

import com.spring.healthease.entity.Patient;
import com.spring.healthease.exception.PatientNotFoundException;
import com.spring.healthease.model.PatientInputModel;
import com.spring.healthease.model.PatientOutputModel;
@Service
public interface PatientService {	
	
	public  PatientOutputModel addPatients(PatientInputModel patientInputModel);
	public PatientOutputModel loginPatients(String username, String password);
	
}
