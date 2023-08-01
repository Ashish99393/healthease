package com.spring.healthease.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.spring.healthease.model.ErrorResponse;

@ControllerAdvice
public class MyExceptionAdvice {
	@ExceptionHandler(BookingException.class)
    public ResponseEntity<ErrorResponse> handleBookingException(BookingException e,WebRequest we){
       
		ErrorResponse er= new ErrorResponse();
        er.setErrorMessage(e.getMessage());
        er.setErrorCode(404);
        return new ResponseEntity<ErrorResponse>(er,HttpStatus.NOT_FOUND);

    }
	
	@ExceptionHandler(DoctorIdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDoctorIdNotFoundException(DoctorIdNotFoundException e,WebRequest we){
		
		ErrorResponse er= new ErrorResponse();
        er.setErrorMessage(e.getMessage());
        er.setErrorCode(404);
        return new ResponseEntity<ErrorResponse>(er,HttpStatus.NOT_FOUND);

    }
	
	@ExceptionHandler(DoctorNotAvaliableException.class)
    public ResponseEntity<ErrorResponse> handleDoctorNotAvaliableException(DoctorNotAvaliableException e,WebRequest we){
		
		ErrorResponse er= new ErrorResponse();
        er.setErrorMessage(e.getMessage());
        er.setErrorCode(404);
        return new ResponseEntity<ErrorResponse>(er,HttpStatus.NOT_FOUND);

    }
	
	@ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePatientNotFoundException(PatientNotFoundException e,WebRequest we){
		
		ErrorResponse er= new ErrorResponse();
        er.setErrorMessage(e.getMessage());
        er.setErrorCode(404);
        return new ResponseEntity<ErrorResponse>(er,HttpStatus.NOT_FOUND);

    }
	@ExceptionHandler(SpecializationNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSpecializationNotFoundException(SpecializationNotFoundException e,WebRequest we){
		
		ErrorResponse er= new ErrorResponse();
        er.setErrorMessage(e.getMessage());
        er.setErrorCode(404);
        return new ResponseEntity<ErrorResponse>(er,HttpStatus.NOT_FOUND);

    }
	@ExceptionHandler(AppointmentCancellationException.class)
    public ResponseEntity<ErrorResponse> handleAppointmentCancellationException(AppointmentCancellationException e,WebRequest we){
		
		ErrorResponse er= new ErrorResponse();
        er.setErrorMessage(e.getMessage());
        er.setErrorCode(404);
        return new ResponseEntity<ErrorResponse>(er,HttpStatus.NOT_FOUND);

    }
	@ExceptionHandler(InvalidRegistrationException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRegistrationException(InvalidRegistrationException e,WebRequest we){
		ErrorResponse er= new ErrorResponse();
        er.setErrorMessage(e.getMessage());
        er.setErrorCode(404);
        return new ResponseEntity<ErrorResponse>(er,HttpStatus.NOT_FOUND);

    }
	
}
