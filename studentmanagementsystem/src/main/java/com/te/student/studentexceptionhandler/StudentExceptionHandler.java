package com.te.student.studentexceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.te.student.studentcustomexception.StudentCustomException;
import com.te.student.studentresponse.StudentResponse;

@RestControllerAdvice
public class StudentExceptionHandler {

	@ExceptionHandler(StudentCustomException.class)
	public ResponseEntity<StudentResponse> customExceptionHandler(StudentCustomException exception) {
		return new ResponseEntity<StudentResponse>(new StudentResponse(true, exception.getMessage(), null),
				HttpStatus.BAD_REQUEST);
	}
}
