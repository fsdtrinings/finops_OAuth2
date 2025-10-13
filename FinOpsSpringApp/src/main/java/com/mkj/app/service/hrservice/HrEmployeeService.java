package com.mkj.app.service.hrservice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mkj.app.entity.Employee;
import com.mkj.app.entity.KYCDocuments;

@Service
public interface HrEmployeeService {

	public String saveEmployee(Employee e);
	public String uploadDocs(KYCDocuments docs);
	public void clearCache();
	
	public Employee getEmployee(int code);
	public List<Employee> getAllEmployees();
	public List<Employee> getEmployeesByTechName(String techName);
	public List<Employee> getEmployeeBySalaryRange(int s1,int s2);
	public Employee getEmployeeByAdharNumber(int adharNumber);
	
	public KYCDocuments getDocs(int adharNumber);
	public Employee linkDocumentsWithEmployee(int adharnumber,
												int empCode);
}
