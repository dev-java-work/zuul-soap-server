package com.ssstsar.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ssstsar.courses.CourseDetails;
import com.ssstsar.courses.GetCourseDetailsRequest;
import com.ssstsar.courses.GetCourseDetailsResponse;
import com.ssstsar.soap.webservices.soapcoursemanagement.soap.bean.Course;
import com.ssstsar.soap.webservices.soapcoursemanagement.soap.exception.CourseNotFoundException;
import com.ssstsar.soap.webservices.soapcoursemanagement.soap.service.CourseDetailsService;

@RestController
public class HomeController {

	@Autowired
	CourseDetailsService service;

	
	@RequestMapping("/GetCourseDetailsRequest")
	//@PayloadRoot(namespace = "http://ssstsar.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {

		Course course = service.findById(1);

		if (course == null)
			throw new CourseNotFoundException("Invalid Course Id " + request.getId());

		return mapCourseDetails(course);
	}
	
	
	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		response.setCourseDetails(mapCourse(course));
		return response;
	}
	
	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();

		courseDetails.setId(course.getId());

		courseDetails.setName(course.getName());

		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}
	

}
