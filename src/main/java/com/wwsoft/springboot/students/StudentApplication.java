package com.wwsoft.springboot.students;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Many Spring Boot developers always have their main class annotated 
 * with @Configuration, @EnableAutoConfiguration and @ComponentScan. 
 * Since these annotations are so frequently used together 
 * (especially if you follow the best practices above), 
 * Spring Boot provides a convenient @SpringBootApplication alternative.
 * The @SpringBootApplication annotation is equivalent to 
 * using @Configuration, @EnableAutoConfiguration and @ComponentScan 
 * with their default attributes:
 * 
 * One of the things the @SpringBootApplication does is a component scan. 
 * But, it only scans on sub-packages. i.e. if you put that class in 
 * com.wwsoft.springboot, then it will scan for all classes in sub-packages 
 * i.e. com.wwsoft.springboot.controller.
 */
@SpringBootApplication

/**
 * !!!!!!!!!!!!!!!!!! The following is required for using spring data !!!!!!!!!!!!
 * !!!!!!!!!!!!!!!!!! Without it, SpringDataContactRepository would NOT be able to
 * !!!!!!!!!!!!!!!!!! be injected into the ContactController
 */
@EnableJpaRepositories(basePackages = "com.wwsoft.springboot.students.repository") 

/**
 * !!!!!!!!!!!!!!!!!! The following is required for using spring data !!!!!!!!!!!!!
 * !!!!!!!!!!!!!!!!!! Without it, will get the following exception when deploy 
 * !!!!!!!!!!!!!!!!!! contacts.war
 * !!!!!!!!!!!!!!!!!!		java.lang.IllegalArgumentException: 
 * !!!!!!!!!!!!!!!!!! 		Not an managed type: class com.wwsoft.springboot.data.Contact
 */
@EntityScan(basePackages = "com.wwsoft.springboot.students.data")

/**
 * extending SpringBootServletInitializer is required to deploy it
 * as a war in tomcat.  Otherwise, springboot wouldn't be able
 * to start during war deployment.
 */
public class StudentApplication extends SpringBootServletInitializer {
	/**
	 * Please note this class has an empty body.  As it intends
	 * to run as a war onto tomcat, the main method is ignored 
	 * here too.  For an example of spring boot application being run
	 * as a standalone jar, see wwsoft-spring-boot application.
	 */
}

