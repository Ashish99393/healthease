package com.spring.healthease.util;

import org.springframework.stereotype.Component;

import com.spring.healthease.entity.Appointment;
import com.spring.healthease.model.AppointmentOutputModel;

@Component
public class MapRow {
	
	public AppointmentOutputModel mapRowToAppointmentOutputModel(Appointment appointment) {
		AppointmentOutputModel appOutputModel= new AppointmentOutputModel();
		appOutputModel.setAppDate(appointment.getAppDate());
		appOutputModel.setDocId(appointment.getDoctor().getDocId());
		appOutputModel.setPatId(appointment.getPatient().getPatientId());
		appOutputModel.setAppId(appointment.getAppId());
		appOutputModel.setDocName(appointment.getDoctor().getName());
		appOutputModel.setPatName(appointment.getPatient().getName());
		appOutputModel.setStatus(appointment.getStatus());
		appOutputModel.setReason(appointment.getReason());
		
		return appOutputModel;
	}
	

}
