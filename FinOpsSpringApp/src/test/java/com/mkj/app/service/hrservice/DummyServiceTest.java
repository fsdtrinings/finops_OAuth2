package com.mkj.app.service.hrservice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
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
	
	// Note : there is no @Test annotation
	@ParameterizedTest
	@ValueSource(ints = {-8500,0,10000,18000})
	@Description("this test will throw a IllegalArg Exception if Employee salary is less than 0 or greater than 10000")
	@Disabled
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
	
	
	
	@ParameterizedTest
	@MethodSource("getAllSampleEmployee") 
	@Description("SameTest But through method source_this test will throw a IllegalArg Exception if Employee salary is less than 0 or greater than 10000")
	@Disabled
	void testGetSalaryByUser_EmployeeSalaryIfSalaryOutofbound2(Employee emp)
	{
		// Sample input 
		Employee sampleEmployee = new Employee();
		sampleEmployee.setEmpCode(101);
		sampleEmployee.setSalary(emp.getSalary());
		
		IllegalArgumentException e = assertThrows
				(IllegalArgumentException.class, () -> service.getSalaryByUser(sampleEmployee));
		    /// checking different statement
		//assertEquals("Employee Salary is less than 0", e.getMessage());
		
		assertEquals("Employee Salary is outofbound", e.getMessage());
		
	}
	
	public static List<Employee> getAllSampleEmployee()
	{
	
		List<Employee> list=new ArrayList<>();
		list.add(new Employee(101, null, null, null, -8500, null, null, null));
		list.add(new Employee(102, null, null, null, 0, null, null, null));
		list.add(new Employee(103, null, null, null, 10500, null, null, null));
		list.add(new Employee(104, null, null, null, 10000, null, null, null));
		
		
		return list;
	}
	
	
	@ParameterizedTest
	@CsvSource({
	    "101, A, -8500",
	    "102, B, 0",
	    "103, C, 10000",
	    "104, D, 18000"
	})
	//@Disabled
	void testGetSalaryByUser_EmployeeSalaryOutOfBound(int empCode, String name, int salary) {
	    Employee emp = new Employee();
	    emp.setEmpCode(empCode);
	    emp.setEmpName(name);
	    emp.setSalary(salary);

	    IllegalArgumentException e = assertThrows
				(IllegalArgumentException.class, () -> service.getSalaryByUser(emp));
		

	    assertEquals("Employee Salary is outofbound", e.getMessage());
	}
	
	
	
	
}//end test class





























