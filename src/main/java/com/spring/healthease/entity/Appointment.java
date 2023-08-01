package com.spring.healthease.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Entity
public class Appointment  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer appId;
	@ManyToOne
	@JoinColumn(name = "pid")
	private Patient patient;
	@ManyToOne
	@JoinColumn(name = "did")
	private Doctor doctor;
	private LocalDate appDate;
	private String reason;
	private String status;
}
