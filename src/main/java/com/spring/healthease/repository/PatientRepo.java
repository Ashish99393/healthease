package com.spring.healthease.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.healthease.entity.Patient;

public interface PatientRepo extends JpaRepository<Patient, Integer>{

	@Query("select p from Patient p where p.name=?1")
	Patient getUsername(String username);

	

	

}
