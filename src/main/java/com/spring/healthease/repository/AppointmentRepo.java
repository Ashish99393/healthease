package com.spring.healthease.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.healthease.entity.Appointment;

public interface AppointmentRepo extends JpaRepository<Appointment, Integer>{

	@Query("select a from Appointment a where a.appId=?1 and a.patient.patientId=?2")
	Appointment findAppointment(Integer appId, Integer patId);
	
	@Query("select a from Appointment a where a.patient.patientId=?1")
	List<Appointment> findAllAppointments( Integer patId);
    @Query("select a from Appointment a where a.patient.patientId=?1 and a.doctor.docId=?2 and a.appDate=?3 and a.status='Confirmed'")
	Object checkValidAppointment(Integer patId, Integer docId, LocalDate appDate, String status);

}
