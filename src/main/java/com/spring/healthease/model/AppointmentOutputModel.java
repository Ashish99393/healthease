package com.spring.healthease.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class AppointmentOutputModel {

	private Integer appId;
	private Integer patId;
	private String patName;	
	private Integer docId;
	private String docName;	
	private String status;	
	private LocalDate appDate;
	private String reason;
	
}
