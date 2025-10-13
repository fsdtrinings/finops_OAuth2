package com.mkj.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mkj.app.entity.Employee;
import com.mkj.app.entity.KYCDocuments;
import com.mkj.app.repository.hrrepo.HREmployeeRepository;
import com.mkj.app.service.hrservice.HrEmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/hr")
@Tag(name = "Employee Entity", description = "Operations related to Employee")
public class HREmployeeController {

	@Autowired
	HrEmployeeService hrService;

	public HREmployeeController() {
		System.out.println("Employee Controller constructor");
	}

	@PostMapping("/employee")
	public String insertEmployee(@RequestBody Employee employee) {

		String str = hrService.saveEmployee(employee);

		return str;
	}

	public ResponseEntity<String> deleteEmployeeRecord(@PathVariable int code) {
		// calling service delete operation

		// after deleting employee
		hrService.clearCache();
		return null;
	}

	@Operation(

			summary = "Operation is used to get Employee by empCode", description = "fetches the unique employee", responses = {
					@ApiResponse(responseCode = "200", description = "Employee Found"),
					@ApiResponse(responseCode = "500", description = "Exception at backend"),

			})

	@GetMapping("/employee/{code}")
	public ResponseEntity<?> getEmployeebyId(
			@Parameter(description = "Provoide unique employee code", example = "1") @PathVariable int code) {
		Employee savedEmployee = hrService.getEmployee(code);

		return new ResponseEntity<Employee>(savedEmployee, HttpStatus.OK);
	}

	@PostMapping("/kycdocs")
	public ResponseEntity<String> uploadKYCDoc(@RequestBody KYCDocuments docs) {
		if (docs != null) {
			String msg = hrService.uploadDocs(docs);
			return new ResponseEntity<String>(msg, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Error! at backend", HttpStatus.BAD_REQUEST);
	}

	// ...../employee/1/doc?an=741852

	@PutMapping("/employee/{code}/doc")
	public ResponseEntity<Employee> linkDocsWithEmployee(@PathVariable int code,
			@RequestParam(required = true) int an) {

		Employee updatedEmployee = hrService.linkDocumentsWithEmployee(an, code);

		return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);
	}

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List<Employee> list = hrService.getAllEmployees();
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}

	// 8085/hr/employee/techname?tn=aws
	@GetMapping("/employees/techname")
	public ResponseEntity<List<Employee>> getEmployeesBytechName(@RequestParam String tn) {
		List<Employee> list = hrService.getEmployeesByTechName(tn);
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}

	// 8085/hr/employee/techname?tn=aws
	@GetMapping("/employees/salary")
	public ResponseEntity<List<Employee>> getEmployeesBySalaryRange(
						@RequestParam(required = true)int s1,
						@RequestParam(required = true)int s2)
	{
		List<Employee> list = hrService.getEmployeeBySalaryRange(s1, s2);
		return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
	}

	
	
	@GetMapping("/employee/adhar/{adhar}")
	public ResponseEntity<Employee> getEmployeeByAdhar(@PathVariable int adhar)
	{
		Employee e = hrService.getEmployeeByAdharNumber(adhar);
		return new ResponseEntity<Employee>(e,HttpStatus.OK);
	}
	
	
}// end class



















