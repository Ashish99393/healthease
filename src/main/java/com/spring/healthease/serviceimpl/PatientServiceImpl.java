package com.spring.healthease.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.healthease.entity.Patient;
import com.spring.healthease.exception.InvalidRegistrationException;
import com.spring.healthease.exception.PatientNotFoundException;
import com.spring.healthease.model.PatientInputModel;
import com.spring.healthease.model.PatientOutputModel;
import com.spring.healthease.repository.PatientRepo;
import com.spring.healthease.util.MapRow;
import com.spring.healthease.util.MapRowPatient;
import com.spring.healthease.validations.PatientValidation;
@Service
public class PatientServiceImpl {
	@Autowired
	PatientRepo patientRepo;
	@Autowired
	MapRowPatient mapRowPatient;
	@Autowired
	PatientValidation patValidation;
	public  PatientOutputModel addPatients(PatientInputModel patientInputModel) throws InvalidRegistrationException
	{
		
			
		if(!patValidation.checkEmail(patientInputModel.getEmail()))
		{
			throw new InvalidRegistrationException("Email not valid");
		}
		if(!patValidation.checkName(patientInputModel.getName()))
		{
			throw new InvalidRegistrationException("Username not valid");
		}
		if(!patValidation.validatePassword(patientInputModel.getPassword()))
		{
			throw new InvalidRegistrationException("Password not valid");
		}
		if(patientRepo.getUsername(patientInputModel.getName())!=null) {
			throw new InvalidRegistrationException("Username already exist");
			
		}
		if(patientInputModel.getAge()>0 && patientInputModel.getAge()<120)
		{
			Patient patient=new Patient();
			
			patient.setAddress(patientInputModel.getAddress());
			patient.setAge(patientInputModel.getAge());
			patient.setEmail(patientInputModel.getEmail());
			patient.setName(patientInputModel.getName());
			patient.setPassword(patientInputModel.getPassword());		
			
			patientRepo.save(patient);
			
			PatientOutputModel patientOutputModel= mapRowPatient.mapToPatientOutputModel(patient);
			return patientOutputModel;
		}
		else {
			throw new InvalidRegistrationException("Age must be between 1 and 119");
		}
		
		
	}
	public PatientOutputModel loginPatients(String username, String password) throws PatientNotFoundException {
		Patient patient= patientRepo.getUsername(username);
		if(patient!=null && patient.getPassword().equals(password)) {
			PatientOutputModel patientOutputModel= mapRowPatient.mapToPatientOutputModel(patient);
			return patientOutputModel;			
		}
		else {
			throw new PatientNotFoundException("Username or password not valid!");
		}
//		return null;
	}
}
