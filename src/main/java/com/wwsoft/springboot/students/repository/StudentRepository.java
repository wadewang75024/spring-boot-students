package com.wwsoft.springboot.students.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wwsoft.springboot.students.data.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
}

