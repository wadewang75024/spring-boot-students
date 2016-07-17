package com.wwsoft.springboot.students.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.wwsoft.springboot.students.data.Student;
import com.wwsoft.springboot.students.exception.StudentNotFoundException;
import com.wwsoft.springboot.students.repository.StudentRepository;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class StudentController {
	
	private StudentRepository studentRepo;
	
	@Autowired
	public StudentController(StudentRepository studentRepo) {
	    this.studentRepo = studentRepo;
	}
	
	@RequestMapping(value="/student/{id}", method=RequestMethod.GET) 
	public @ResponseBody Student student(@PathVariable Long id) throws StudentNotFoundException {
	    System.out.println("***************************** Retrieve one student: " + id);
	    Student c = studentRepo.findOne(id);
	    if (c==null)
	    	throw new StudentNotFoundException(id);
	    return studentRepo.findOne(id);
	}
    
    @RequestMapping(value="/students", method=RequestMethod.GET) 
    public List<Student> contacts() {
    	System.out.println("***************************** findAll");
    	List<Student> contacts = new ArrayList<>();
    	for (Student contact : studentRepo.findAll()) {
    		contacts.add(contact);
		}
    	return contacts;
    }
    
    @RequestMapping(value="/save", method=RequestMethod.POST) 
    /**
     * !!!!!!!!!!!!!!!!!!!!!!! @RequestBody is required here,
     * !!!!!!!!!!!!!!!!!!!!!!! otherwise the input contact will include all null for its
     * !!!!!!!!!!!!!!!!!!!!!!! attribute values (first name, last name and so on)
     */
    public ResponseEntity<Student> save(@RequestBody Student c, UriComponentsBuilder ucb) {
    	System.out.println("***************************** save");
    	System.out.println("***************************** Contact to be saved: " + " " + 
    						c.getFirstName() + " " +
    						c.getLastName()  + " " + 
    						c.getEmailAddress());
    	Student student = studentRepo.save(c);
    	HttpHeaders headers = new HttpHeaders();
		URI locationUri = 
				ucb.path("/student/").path(String.valueOf((long)student.getId())).build().toUri();
		System.out.println("locationUri : " + locationUri.toString() );
		headers.setLocation(locationUri);
		ResponseEntity<Student> responseEntity = 
				new ResponseEntity<Student> (student, headers, HttpStatus.CREATED);
		return responseEntity;
    }
}

