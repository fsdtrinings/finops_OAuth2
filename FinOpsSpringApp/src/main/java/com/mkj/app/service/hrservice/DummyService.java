package com.mkj.app.service.hrservice;

import org.springframework.stereotype.Service;

import com.mkj.app.entity.Employee;

@Service
public class DummyService {

	
	public int doSomeWork(int a,int b)
	{
		int sum = a+b;
		return sum;
	}
	
	public int getSalaryByUser(Employee e)
	{
		int salary = 0; // 2500 , 20% : 500/- , 2000
		if(e != null)
		{
			 salary = e.getSalary();
			 // logic was wrong , used && instead of ||
			 if(salary<=0 || salary >= 10000)
			 {
				 throw new IllegalArgumentException("Employee Salary is outofbound");
			 }
			 int tax = (int)(salary * 0.20);
			 salary -= tax;
		}
		else
		{
			throw new NullPointerException("Employee is null");
		}
		
		return salary; // 2000
		
	}
	
}
