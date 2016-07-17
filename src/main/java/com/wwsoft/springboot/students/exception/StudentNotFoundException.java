package com.wwsoft.springboot.students.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND,
			    reason="This student does not exist.")
public class StudentNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	private long studentId;
	
	public StudentNotFoundException(long studentId) {
		System.out.println("***************************** StudentNotFoundException");
		this.studentId = studentId;
	}
	public long getStudentId() {
		return studentId;
	}
}

