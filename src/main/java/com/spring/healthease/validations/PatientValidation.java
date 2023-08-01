package com.spring.healthease.validations;

import org.springframework.stereotype.Component;

import com.spring.healthease.exception.InvalidRegistrationException;

@Component
public class PatientValidation {
	
	public boolean checkName(String name) throws InvalidRegistrationException {
        if (name.matches("[A-Za-z ]+")) {
            return true; 

        } else {
            throw new InvalidRegistrationException("Invalid Name");
        }
    } 

    public boolean checkEmail(String email) throws InvalidRegistrationException {
        if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return true;
        } else {
            throw new InvalidRegistrationException("Invalid Email Id");
        }
    }
    
    public boolean validatePassword(String password) throws InvalidRegistrationException {
        if (password.length() < 6) {
            throw new InvalidRegistrationException("Password too short");
        } else if (password.matches("[A-Za-z]+")) {
            throw new InvalidRegistrationException("Password should also contain number");
        } else if (password.matches("[0-9]+")) {
            throw new InvalidRegistrationException("Password should also contain alphabets");
        }
        
        return true;
}}
