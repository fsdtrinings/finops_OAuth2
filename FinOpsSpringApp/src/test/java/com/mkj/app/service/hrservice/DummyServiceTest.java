package com.mkj.app.service.hrservice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.context.annotation.Description;

import com.mkj.app.entity.Employee;

/* This is the test of Basic*/
class DummyServiceTest {

	
	static DummyService service;
	
	@BeforeAll
	static void doInit()
	{
		service = new DummyService();
	}
	
	
	@Test
	@Disabled
	void testDoSomeWork() {
	
		// Sample input
		int sampleInput1 = 10;
		int sampleInput2 = 20;
		// expected Output
		int expectedOutput = 30;
		
		// actual class object which i want to test // class under test
		//DummyService service = new DummyService(); // initilized in doInit method
		
		// calling actual method // method under test
		int actualOutput = service.doSomeWork(sampleInput1, sampleInput2);
		
		// comparing the outputs
		assertEquals(expectedOutput, actualOutput);
		
		
		
	}
	
	
	@Test
	@Disabled
	@Description("this test will pass if we get any salary > 0 for valid employee")
	void testGetSalaryByUser_ValidScenario()
	{
		// Sample input 
		Employee sampleEmployee = new Employee();
		sampleEmployee.setEmpCode(101);
		sampleEmployee.setSalary(2500);
		
		// sample output 
		int actualOutput;
		int expectedOutput = 2000;
		
		
		// call actual method
		actualOutput = service.getSalaryByUser(sampleEmployee);
		
		// use assert satements
		assertEquals(expectedOutput, actualOutput);
		
	}
	
	
	@Test
	@Description("this test will throw a NullPointerException if Employee is null")
	@Disabled
	void testGetSalaryByUser_EmployeeIsNull()
	{
		// Sample input 
		Employee sampleEmployee = null;
		
		NullPointerException e = assertThrows
				(NullPointerException.class, () -> service.getSalaryByUser(sampleEmployee));
		
		assertEquals("Employee is null", e.getMessage());

	
	}
	
	@Test
	@Description("this test will throw a IllegalArg Exception if Employee salary is less than 0")
	@Disabled
	void testGetSalaryByUser_EmployeeSalaryIsNotValid()
	{
		// Sample input 
		Employee sampleEmployee = new Employee();
		sampleEmployee.setEmpCode(101);
		sampleEmployee.setSalary(-8500);
		
		IllegalArgumentException e = assertThrows
				(IllegalArgumentException.class, () -> service.getSalaryByUser(sampleEmployee));
		
		assertEquals("Employee Salary is less than 0", e.getMessage());

	
	}
	

	@ParameterizedTest
	@ValueSource(ints = {-8500,0,10000,18000})
	//@Test // 1st Issue
	@Description("this test will throw a IllegalArg Exception if Employee salary is less than 0 or greater than 10000")

	//@Disabled
	void testGetSalaryByUser_EmployeeSalaryIfSalaryOutofbound(int salary)
	{
		// Sample input 
		Employee sampleEmployee = new Employee();
		sampleEmployee.setEmpCode(101);
		sampleEmployee.setSalary(salary);
		
		IllegalArgumentException e = assertThrows
				(IllegalArgumentException.class, () -> service.getSalaryByUser(sampleEmployee));
		    /// checking different statement
		//assertEquals("Employee Salary is less than 0", e.getMessage());
		
		assertEquals("Employee Salary is outofbound", e.getMessage());
		
	}
	
}//end test class





























