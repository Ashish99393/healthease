package com.spring.healthease.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ErrorResponse {
	private Integer errorCode;
	private String errorMessage;

}
