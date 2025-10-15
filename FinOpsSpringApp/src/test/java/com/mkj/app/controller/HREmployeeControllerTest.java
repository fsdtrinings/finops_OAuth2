package com.mkj.app.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkj.app.entity.Employee;
import com.mkj.app.entity.ResponseEmployeeDTO;
import com.mkj.app.service.hrservice.HrEmployeeService;



@SpringBootTest // its loads the full application context for testing
@AutoConfigureMockMvc // allows to use http lib
class HREmployeeControllerTest {

	@Autowired
	MockMvc mockMvc; // similar to Postman, Swagger
	
	@Autowired
	ObjectMapper objectmapper;
	
	@MockBean
	private HrEmployeeService service;
	
	
	@Test
	void test_insertEmployee() throws Exception
	{
		
		Employee sampleInput = new Employee();
		sampleInput.setEmpName("Test-Employee");
		sampleInput.setTechName("test-Tech");
		sampleInput.setDesignation("Sample Designation");
		sampleInput.setSalary(5000);
		
		mockMvc.perform(post("/hr/employee")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectmapper.writeValueAsString(sampleInput))
				);
		
		Mockito.verify(service,times(1)).saveEmployee(Mockito.any(Employee.class));
	
	}
	
	
	@Test
	void test_getEmployeebyId() throws Exception
	{
		
		/*
		 * Sample output 
		  {
    			"id": 1,
    			"name": "Suresh"
			}
		
		 * */
		
		Employee e = new Employee(); // 
		e.setEmpCode(1);
		e.setEmpName("testname");
		
		
		//Mockito.when(______ actual call to the service method).thenReturn(--expectedValues);
		
		Mockito.when(service.getEmployee(1)).thenReturn(e);
		
		
		// actual call 
		mockMvc.perform(get("/hr/employee/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.name").value("testname"));
		
		Mockito.verify(service,times(1)).getEmployee(Mockito.anyInt());	
	
	}
	
	
	
	@Test
	void test_getEmployees() throws Exception
	{
		
		/*
		 * Sample output 
		  {
    			"id": 1,
    			"name": "Suresh"
			}
			{
    			"id": 2,
    			"name": "Suresh"
			}
			{
    			"id": 3,
    			"name": "Suresh"
			}
			
		
		 * */
		
		Employee e1 = new Employee(); // 
		e1.setEmpCode(1);
		e1.setEmpName("testname-1");
		
		Employee e2 = new Employee(); // 
		e2.setEmpCode(2);
		e2.setEmpName("testname-2");
		
		List<Employee> empList = Arrays.asList(e1,e2);
		
		
		//Mockito.when(______ actual call to the service method).thenReturn(--expectedValues);
		
		Mockito.when(service.getAllEmployees()).thenReturn(empList);
		
		
		// actual call 
		mockMvc.perform(get("/hr/employees"))
			.andExpect(status().isOk())
			//.andExpect(jsonPath("$", hasSize(2))
			.andExpect(jsonPath("$[0].id").value(e1.getEmpCode()))
			.andExpect(jsonPath("$[0].name").value(e1.getEmpName()))
			.andExpect(jsonPath("$[1].id").value(e2.getEmpCode()))
			.andExpect(jsonPath("$[1].name").value(e2.getEmpName()));
			
		
		Mockito.verify(service,times(1)).getAllEmployees();	
	
	}
	

}










