package com.spring.healthease.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.healthease.entity.Appointment;
import com.spring.healthease.exception.AppointmentCancellationException;
import com.spring.healthease.exception.AppointmentNotFoundException;
import com.spring.healthease.exception.BookingException;
import com.spring.healthease.exception.DoctorIdNotFoundException;
import com.spring.healthease.exception.PatientNotFoundException;
import com.spring.healthease.model.AppointmentInputModel;
import com.spring.healthease.model.AppointmentOutputModel;
import com.spring.healthease.serviceimpl.AppointmentServiceImpl;
import com.spring.healthease.services.AppointmentService;

@CrossOrigin("*")
@RestController
public class AppointmentController {
	
	@Autowired
	AppointmentServiceImpl appService;
	Logger logger=LoggerFactory.getLogger(AppointmentController.class);
	@PostMapping("/bookappointment")
	public ResponseEntity<AppointmentOutputModel> bookAppointment(@RequestBody AppointmentInputModel appModel) throws PatientNotFoundException, DoctorIdNotFoundException, BookingException{
		logger.info("bookAppointment() method started={}",appModel);
		AppointmentOutputModel appointmentOutputModel = appService.bookAppointment(appModel);
		logger.info("Appointment Book={}", appointmentOutputModel);
		logger.info("bookAppointment() method ended");
		return new ResponseEntity<AppointmentOutputModel>(appointmentOutputModel, HttpStatus.OK) ;
	}
	
	@PutMapping("/cancelappointment/{appid}")
	public ResponseEntity<AppointmentOutputModel>  cancelAppointment (@PathVariable("appid") Integer appId,@RequestParam Integer patId) throws AppointmentNotFoundException, AppointmentCancellationException {
		logger.info("cancelAppointment() method started");
		AppointmentOutputModel appointmentOutputModel = appService.cancelAppointment(appId, patId);
		logger.info("Appointment Cancelled={}", appointmentOutputModel);
		logger.info("bookAppointment() method ended");
		return new ResponseEntity<AppointmentOutputModel>(appointmentOutputModel, HttpStatus.OK);
	}
	
	@GetMapping("/getappointment/{patid}")
	public ResponseEntity<List<AppointmentOutputModel> > getAllAppointments(@PathVariable("patid") Integer patId){
		logger.info("getAllAppointments() method started");
		List<AppointmentOutputModel> appointments=appService.getAllAppointments(patId);
		logger.info("Appointments={}", appointments);
		logger.info("getAllAppointments() method ended");
		return new ResponseEntity<List<AppointmentOutputModel> >(appointments, HttpStatus.OK);
		
	}

}
