package com.spring.healthease.serviceimpl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.healthease.entity.Appointment;
import com.spring.healthease.entity.Doctor;
import com.spring.healthease.entity.Patient;
import com.spring.healthease.exception.AppointmentCancellationException;
import com.spring.healthease.exception.AppointmentNotFoundException;
import com.spring.healthease.exception.BookingException;
import com.spring.healthease.exception.DoctorIdNotFoundException;
import com.spring.healthease.exception.DoctorNotAvaliableException;
import com.spring.healthease.exception.PatientNotFoundException;
import com.spring.healthease.model.AppointmentInputModel;
import com.spring.healthease.model.AppointmentOutputModel;
import com.spring.healthease.repository.AppointmentRepo;
import com.spring.healthease.repository.DoctorRepo;
import com.spring.healthease.repository.PatientRepo;
import com.spring.healthease.services.AppointmentService;
import com.spring.healthease.util.MapRow;

@Service
public class AppointmentServiceImpl implements AppointmentService{
	
	@Autowired
	DoctorRepo docRepo;
	@Autowired
	PatientRepo pateintRepo;
	@Autowired
	AppointmentRepo appRepo;
	@Autowired
	MapRow mapRow;
	
	public AppointmentOutputModel bookAppointment(AppointmentInputModel appModel) throws PatientNotFoundException, DoctorIdNotFoundException, BookingException {
		
		Patient pat= pateintRepo.findById(appModel.getPatId()).orElse(null);
		Doctor doc= docRepo.findById(appModel.getDocId()).orElse(null);	
		
		if(pat==null)
			throw new PatientNotFoundException("Not a valid Patient");
		if(doc==null)
			throw new DoctorIdNotFoundException("Doctor Not Found");
		
		LocalDate localDate= LocalDate.now();
		if(localDate.isAfter(appModel.getAppDate())) {
			throw new BookingException("Booking date cannot be less than current date");
		}
		
		DayOfWeek dayOfWeek= appModel.getAppDate().getDayOfWeek();
		String weekDay= dayOfWeek.name();
	
		String[] days= doc.getAvailableDays().split(",");
		
		boolean f=false;
		for(String s:days) {
			
			if(s.equalsIgnoreCase(weekDay)) {
				f=true;
				break;
			}
		}
		
		if(!f)
			throw new BookingException("Doctor not available");
		
		if(appRepo.checkValidAppointment(appModel.getPatId(),appModel.getDocId(),appModel.getAppDate(),appModel.getStatus())==null) {
			
			Appointment appointment= new Appointment();
			appointment.setDoctor(doc);
			appointment.setPatient(pat);
			appointment.setStatus("Confirmed");
			appointment.setAppDate(appModel.getAppDate());
			appointment.setReason(appModel.getReason());
			
			appointment= appRepo.save(appointment);
			
			AppointmentOutputModel appOutputModel= mapRow.mapRowToAppointmentOutputModel(appointment);
			
			return appOutputModel;
		}
		else
		{
			throw new BookingException("Same appointment is not possible on same date");
		}
		
	}
	public AppointmentOutputModel cancelAppointment(Integer appId, Integer patId) throws AppointmentNotFoundException, AppointmentCancellationException {
		
		Appointment appointment= appRepo.findAppointment(appId,patId);
		 if (appointment == null) {
		        // Handle the case when the appointment is not found
		        throw new AppointmentNotFoundException("Appointment not found");
		    }
		 LocalDate todayDate= LocalDate.now();
		 LocalDate appDate= appointment.getAppDate();
		 long dayDiff= ChronoUnit.DAYS.between(todayDate, appDate);
		 System.out.println(dayDiff);
		 if(dayDiff<=2 || (dayDiff<0 ))
			 throw new AppointmentCancellationException("Cannot Cancel the appointment");
		
		appointment.setStatus("Cancelled");
		
		appointment= appRepo.save(appointment);
		
		AppointmentOutputModel appOutputModel= mapRow.mapRowToAppointmentOutputModel(appointment);		
		return appOutputModel;
	}

	public List<AppointmentOutputModel> getAllAppointments(Integer patId) {
		List<Appointment> lst = appRepo.findAllAppointments(patId);
		List<AppointmentOutputModel> outputs=new ArrayList<>();
		
		for(Appointment appointment:lst) {
			AppointmentOutputModel appOutputModel= mapRow.mapRowToAppointmentOutputModel(appointment);
			
			outputs.add(appOutputModel);
		}
		return outputs;
	}
		
	
}
