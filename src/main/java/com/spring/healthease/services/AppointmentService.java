package com.spring.healthease.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.healthease.exception.AppointmentCancellationException;
import com.spring.healthease.exception.AppointmentNotFoundException;
import com.spring.healthease.exception.BookingException;
import com.spring.healthease.exception.DoctorIdNotFoundException;
import com.spring.healthease.exception.PatientNotFoundException;
import com.spring.healthease.model.AppointmentInputModel;
import com.spring.healthease.model.AppointmentOutputModel;

@Service
public interface AppointmentService {
	
	
	public AppointmentOutputModel bookAppointment(AppointmentInputModel appModel) throws PatientNotFoundException, DoctorIdNotFoundException, BookingException ;
	public AppointmentOutputModel cancelAppointment(Integer appId, Integer patId) throws AppointmentNotFoundException,AppointmentCancellationException;
	public List<AppointmentOutputModel> getAllAppointments(Integer patId);
}
