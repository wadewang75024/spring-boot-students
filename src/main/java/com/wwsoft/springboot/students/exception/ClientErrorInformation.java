package com.wwsoft.springboot.students.exception;

public class ClientErrorInformation {
	public String exception;
	public String uri;
	
	public ClientErrorInformation(String exception, String uri) {
		this.exception = exception;
		this.uri = uri;
	}
}
