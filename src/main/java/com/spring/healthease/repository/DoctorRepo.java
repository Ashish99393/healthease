package com.spring.healthease.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.healthease.entity.Doctor;

public interface DoctorRepo extends JpaRepository<Doctor, Integer>{
    @Query("select d from Doctor d where d.specialization like %:key%")
	List<Doctor> findBySpecialization(@Param("key")  String speciality);
    
    
    
    
    

    

}
