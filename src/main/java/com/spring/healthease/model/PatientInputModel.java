package com.spring.healthease.model;

import lombok.Data;

@Data
public class PatientInputModel {
	private Integer age;
	private String name;
	private String email;
	private String password;
	private String address;
}
