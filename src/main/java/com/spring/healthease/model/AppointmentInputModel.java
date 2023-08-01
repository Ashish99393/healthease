package com.spring.healthease.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentInputModel {

	private Integer docId;
	private Integer patId;
	private LocalDate appDate;
	private String reason;
	private String status;

}
