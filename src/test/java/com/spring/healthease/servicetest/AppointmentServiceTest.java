package com.spring.healthease.servicetest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.healthease.entity.Appointment;
import com.spring.healthease.entity.Doctor;
import com.spring.healthease.entity.Patient;
import com.spring.healthease.exception.AppointmentCancellationException;
import com.spring.healthease.exception.AppointmentNotFoundException;
import com.spring.healthease.exception.BookingException;
import com.spring.healthease.exception.DoctorIdNotFoundException;
import com.spring.healthease.exception.PatientNotFoundException;
import com.spring.healthease.model.AppointmentInputModel;
import com.spring.healthease.model.AppointmentOutputModel;
import com.spring.healthease.repository.AppointmentRepo;
import com.spring.healthease.repository.DoctorRepo;
import com.spring.healthease.repository.PatientRepo;
import com.spring.healthease.serviceimpl.AppointmentServiceImpl;
import com.spring.healthease.util.MapRow;

import nonapi.io.github.classgraph.utils.Assert;

@ExtendWith(SpringExtension.class)
public class AppointmentServiceTest {
    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @Mock
    private AppointmentRepo appRepo;

    @Mock
    private MapRow mapRow;
    
    @Mock
    private PatientRepo patRepo;
    
    @Mock
    private DoctorRepo docRepo;
    
    @Test

    public void testBookAppointment() throws PatientNotFoundException, DoctorIdNotFoundException, BookingException {
        AppointmentInputModel appModel = new AppointmentInputModel();
        appModel.setPatId(1);
        appModel.setDocId(1);
        appModel.setAppDate(LocalDate.now().plusDays(1));
        appModel.setReason("Checkup");
 

        Patient patient = new Patient();
        patient.setPatientId(1);
        when(patRepo.findById(appModel.getPatId())).thenReturn(Optional.of(patient));
 

        Doctor doctor = new Doctor();
        doctor.setDocId(1);
        doctor.setAvailableDays("MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY");
        when(docRepo.findById(appModel.getDocId())).thenReturn(Optional.of(doctor));        

        LocalDate currentDate = LocalDate.now();       

        AppointmentOutputModel outputModel = new AppointmentOutputModel();
        outputModel.setAppId(1);
        outputModel.setAppDate(appModel.getAppDate());
        outputModel.setReason(appModel.getReason());
        outputModel.setStatus("Confirmed");
        outputModel.setPatId(appModel.getPatId());
        outputModel.setDocId(appModel.getDocId());

        when(mapRow.mapRowToAppointmentOutputModel(any(Appointment.class))).thenReturn(outputModel);

        Appointment savedAppointment = new Appointment();
        savedAppointment.setAppId(1);
        when(appRepo.save(any(Appointment.class))).thenReturn(savedAppointment);
      

        AppointmentOutputModel result = appointmentService.bookAppointment(appModel);

        assertEquals(appModel.getAppDate(), result.getAppDate());
        assertEquals(appModel.getReason(), result.getReason());
        assertEquals("Confirmed", result.getStatus());
        assertEquals(appModel.getPatId(), result.getPatId());
        assertEquals(appModel.getDocId(), result.getDocId());

 

    }

    @Test
    public void testCancelAppointment() throws Exception {
        // Arrange
        Integer appId = 1;
        Integer patId = 1;

        Appointment appointment = new Appointment();
        appointment.setAppId(appId);
        appointment.setStatus("Scheduled");

        Mockito.when(appRepo.findAppointment(appId, patId)).thenReturn(appointment);
        Mockito.when(appRepo.save(appointment)).thenReturn(appointment);

        AppointmentOutputModel expectedOutputModel = new AppointmentOutputModel();
        expectedOutputModel.setAppId(appId);
        expectedOutputModel.setStatus("Cancelled");

        Mockito.when(mapRow.mapRowToAppointmentOutputModel(appointment)).thenReturn(expectedOutputModel);

        // Act
        AppointmentOutputModel result = appointmentService.cancelAppointment(appId, patId);

        // Assert
        assertEquals(expectedOutputModel, result);
        assertEquals("Cancelled", appointment.getStatus());
        Mockito.verify(appRepo).save(appointment);
    }


    @Test
    public void testGetAllAppointments() {
        
        Integer patId = 1;
        Appointment appointment1 = new Appointment();
        appointment1.setAppId(1);
        appointment1.setAppDate(LocalDate.now());
        appointment1.setReason("Checkup");
        appointment1.setStatus("Scheduled");
        Patient patient1 = new Patient();
        patient1.setPatientId(1);
        appointment1.setPatient(patient1);
        Doctor doctor1 = new Doctor();
        doctor1.setDocId(1);
        appointment1.setDoctor(doctor1);

        Appointment appointment2 = new Appointment();
        appointment2.setAppId(2);
        appointment2.setAppDate(LocalDate.now());
        appointment2.setReason("Follow-up");
        appointment2.setStatus("Completed");
        Patient patient2 = new Patient();
        patient2.setPatientId(1);
        appointment2.setPatient(patient2);
        Doctor doctor2 = new Doctor();
        doctor2.setDocId(2);
        appointment2.setDoctor(doctor2);

        List<Appointment> mockAppointments = new ArrayList<>();
        mockAppointments.add(appointment1);
        mockAppointments.add(appointment2);

        // Mock the repository method
        when(appRepo.findAllAppointments(patId)).thenReturn(mockAppointments);

        // Mock the mapping method
        AppointmentOutputModel outputModel1 = new AppointmentOutputModel();
        outputModel1.setAppId(1);
        outputModel1.setAppDate(LocalDate.now());
        outputModel1.setReason("Checkup");
        outputModel1.setStatus("Scheduled");
        outputModel1.setPatId(1);
        outputModel1.setDocId(1);

        AppointmentOutputModel outputModel2 = new AppointmentOutputModel();
        outputModel2.setAppId(2);
        outputModel2.setAppDate(LocalDate.now());
        outputModel2.setReason("Follow-up");
        outputModel2.setStatus("Completed");
        outputModel2.setPatId(1);
        outputModel2.setDocId(2);

        when(mapRow.mapRowToAppointmentOutputModel(appointment1)).thenReturn(outputModel1);
        when(mapRow.mapRowToAppointmentOutputModel(appointment2)).thenReturn(outputModel2);

        // Call the method under test
        List<AppointmentOutputModel> result = appointmentService.getAllAppointments(patId);

        // Assertions
        assertEquals(2, result.size());

        AppointmentOutputModel resultModel1 = result.get(0);
        assertEquals(1, resultModel1.getAppId());
        assertEquals(LocalDate.now(), resultModel1.getAppDate());
        assertEquals("Checkup", resultModel1.getReason());
        assertEquals("Scheduled", resultModel1.getStatus());
        assertEquals(1, resultModel1.getPatId());
        assertEquals(1, resultModel1.getDocId());

        AppointmentOutputModel resultModel2 = result.get(1);
        assertEquals(2, resultModel2.getAppId());
        assertEquals(LocalDate.now(), resultModel2.getAppDate());
        assertEquals("Follow-up", resultModel2.getReason());
        assertEquals("Completed", resultModel2.getStatus());
        assertEquals(1, resultModel2.getPatId());
        assertEquals(2, resultModel2.getDocId());
    }
}