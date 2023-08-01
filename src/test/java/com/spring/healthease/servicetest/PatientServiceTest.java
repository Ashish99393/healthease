package com.spring.healthease.servicetest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.healthease.entity.Patient;
import com.spring.healthease.model.PatientInputModel;
import com.spring.healthease.model.PatientOutputModel;
import com.spring.healthease.repository.PatientRepo;
import com.spring.healthease.serviceimpl.PatientServiceImpl;
import com.spring.healthease.services.PatientService;
import com.spring.healthease.util.MapRowPatient;
import com.spring.healthease.validations.PatientValidation;
@ExtendWith(SpringExtension.class)
public class PatientServiceTest {
    
    @Mock
    private PatientRepo patientRepository;
    
    @Mock
    private PatientValidation patientValidation;
    
        
    @InjectMocks
    private PatientServiceImpl patientService;
    
    @Mock
    private MapRowPatient patientMapper;
    
    @Test
    public void testAddPatients() throws Exception {
        // Arrange
        PatientInputModel inputModel = new PatientInputModel();
        inputModel.setName("John Doe");
        inputModel.setAge(30);
        inputModel.setEmail("johndoe@example.com");
        inputModel.setPassword("P@ssw0rd");
        when(patientValidation.checkName(inputModel.getEmail())).thenReturn(true);
        when(patientValidation.checkName(inputModel.getName())).thenReturn(true);
        when(patientValidation.validatePassword(inputModel.getPassword())).thenReturn(true);
        Patient patient=new Patient();
        patient.setPatientId(1);
        patient.setAddress(inputModel.getAddress());
        patient.setAge(inputModel.getAge());
        patient.setEmail(inputModel.getEmail());
        patient.setName(inputModel.getName());
        patient.setPassword(inputModel.getPassword());
        
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);
        when(patientMapper.mapToPatientOutputModel(any(Patient.class))).thenReturn(new PatientOutputModel());
        
        // Act
        PatientOutputModel outputModel = patientService.addPatients(inputModel);
        
        // Assert
        verify(patientValidation, times(1)).checkName(eq(inputModel.getEmail()));
        verify(patientValidation, times(1)).checkName(eq(inputModel.getName()));
        verify(patientValidation, times(1)).validatePassword(eq(inputModel.getPassword()));
        verify(patientRepository, times(1)).save(any(Patient.class));
       
        Assertions.assertNotNull(outputModel);
    }
}