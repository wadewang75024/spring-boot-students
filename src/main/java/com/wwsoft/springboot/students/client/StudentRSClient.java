package com.wwsoft.springboot.students.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.wwsoft.springboot.students.client.Student;

public class StudentRSClient {
	
	/**
	 * This method return all the students.
	 */
	public static void getContacts() {
		System.out.println("******************* Retrieve all contacts");
		String url = "http://localhost:8080/students-RS/students/";
		RestTemplate client = new RestTemplate();
		
		/**
		 * Add MappingJackson2HttpMessageConverter, otherwise it does not know
		 * how to handle the returned list in JSON format.
		 */
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new MappingJackson2HttpMessageConverter());
		client.setMessageConverters(messageConverters);
		
		Student[] students = client.getForObject(url, Student[].class);
		System.out.println("Returned students: " + students.length);
		for (Student contact: students) { 
			System.out.println(contact.toString());	
		}
	}
	
	/**
	 * This method is used to create a contact.
	 * @param contact
	 * @return
	 */
	public static Student saveContact(Student student) {	
		System.out.println("\n\n******************* Save student: " + 
				student.getFirstName() + " " + 
				student.getLastName() + " " + 
				student.getEmailAddress()  );
		String url = "http://localhost:8080/students-RS/save";
		RestTemplate client = new RestTemplate();
		
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new MappingJackson2HttpMessageConverter());
		client.setMessageConverters(messageConverters);

		HttpHeaders requestHeaders = new HttpHeaders();
		HttpEntity<Student>  requestEntity =
				new HttpEntity<Student>(student, requestHeaders);
		
		ResponseEntity<Student> responseEntity = 
				client.exchange(url, HttpMethod.POST, requestEntity, Student.class);	
		
		System.out.println("Response status code: " + responseEntity.getStatusCode());
		Student returnedContact = responseEntity.getBody();
		System.out.println("Returned student: \n" + returnedContact.toString());	
		HttpHeaders headers = responseEntity.getHeaders();
		System.out.println("New location URL: " + headers.getLocation());
		return returnedContact;
	}	
	
	public static void main(String[] args) {
		/**
		 * Retrieve all students
		 */
		// getContacts();
		
		/** 
		 * Create a new student
		 */
		Student student = new Student("Wade", "Wang", "wwang@wwsoft.com");
		student = saveContact(student);
		
		Student mary = new Student("Mary", "Wang", "mwang@wwsoft.com");
		mary = saveContact(mary);
		
		Student tony = new Student("tony", "Wang", "twang@wwsoft.com");
		tony = saveContact(tony);
	}
}
