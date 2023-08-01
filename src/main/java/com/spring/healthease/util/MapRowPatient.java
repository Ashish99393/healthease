package com.spring.healthease.util;

import org.springframework.stereotype.Component;

import com.spring.healthease.entity.Patient;
import com.spring.healthease.model.PatientOutputModel;

@Component
public class MapRowPatient {

	public PatientOutputModel mapToPatientOutputModel( Patient patient ) {
		PatientOutputModel patientOutputModel= new PatientOutputModel();
		patientOutputModel.setAddress(patient.getAddress());
		patientOutputModel.setAge(patient.getAge());
		patientOutputModel.setEmail(patient.getEmail());
		patientOutputModel.setName(patient.getName());
		patientOutputModel.setPatientId(patient.getPatientId());
		return patientOutputModel;
	}
}
