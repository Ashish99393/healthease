package com.spring.healthease.model;

import lombok.Data;

@Data
public class PatientOutputModel {
	
	private Integer patientId;
	private Integer age;
	private String name;
	private String email;
	private String address;

}
