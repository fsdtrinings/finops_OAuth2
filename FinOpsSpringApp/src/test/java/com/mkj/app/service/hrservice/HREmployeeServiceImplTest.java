package com.mkj.app.service.hrservice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mkj.app.entity.Employee;
import com.mkj.app.entity.KYCDocuments;
import com.mkj.app.repository.hrrepo.HREmployeeRepository;

@ExtendWith(MockitoExtension.class)
class HREmployeeServiceImplTest {

	@InjectMocks
	HREmployeeServiceImpl service;
	
	@Mock
	HREmployeeRepository empRepo;
	
	@Test
	//@Disabled
	void testSaveEmployee() 
	{
		
		Employee sampleInput = new Employee();
		sampleInput.setEmpName("A");
		sampleInput.setSalary(3000);
		sampleInput.setDesignation("developer");
		
		Employee sampleOutput = new Employee();
		sampleOutput.setEmpCode(1);
		sampleOutput.setEmpName("A");
		sampleOutput.setSalary(3000);
		sampleOutput.setDesignation("developer");
		
		
		// how to configure Mockito
		//Mockito.when(empRepo.save(--Sample Input---)).thenReturn(--sample expected output---);
		Mockito.when(empRepo.save(sampleInput)).thenReturn(sampleOutput);
		String expectedOutput = sampleOutput.getEmpCode()+" "+sampleOutput.getEmpName()+" Saved";
		
		// call to actual method
		String actualOutput = service.saveEmployee(sampleInput);
		
		
		// assert statement
		assertEquals(expectedOutput, actualOutput);
		
		Mockito.verify(empRepo, Mockito.atLeast(1)).save(Mockito.any(Employee.class));
		Mockito.verify(empRepo, Mockito.never()).delete(Mockito.any(Employee.class));
		Mockito.verify(empRepo, Mockito.never()).findById(Mockito.anyInt());
		
		
		
	}
	
	
	@Test
	//@Disabled
	public void testSaveEmployee_WhenEmployeeIsNull()
	{
		Employee sampleEmployee = null;
		String msg = "Obj is null";
		
		// configuration
	 //Mockito.when(empRepo.save(sampleEmployee)).thenThrow(new NullPointerException(msg));
		
		// actual code
	   NullPointerException exp = assertThrows(NullPointerException.class, ()->{
			service.saveEmployee(sampleEmployee);
		});
		
	   assertEquals(exp.getMessage(), msg);
	   Mockito.verify(empRepo, Mockito.never()).save(Mockito.any(Employee.class));

	}
	
	// ---- test find By Id -----
	@ParameterizedTest
	@ValueSource(ints = {1,5})
	//@Test
	//@Disabled
	public void getEmployee_validCase(int code)
	{
		
		// Sample input & sample output
		int sampleInput = code;
		
		Employee sampleOutput = new Employee();
		sampleOutput.setEmpCode(sampleInput);
		sampleOutput.setEmpName("A");
		sampleOutput.setSalary(3000);
		sampleOutput.setDesignation("developer");
		
		// configuration of Mockito
		Mockito.when(empRepo.findById(sampleInput)).thenReturn(Optional.of(sampleOutput));
		
		// actual call
		Employee actualEmployee = service.getEmployee(sampleInput);
		
		// assert statements 
		assertEquals(sampleOutput, actualEmployee);
		assertNotNull(actualEmployee);
		
		// verification for more better testing
		Mockito.verify(empRepo,Mockito.times(1)).findById(Mockito.anyInt());
		
	}
	
	@ParameterizedTest
	@ValueSource(ints = {0,-5})
	//@Test
	//@Disabled
	public void getEmployee_InvalidCodes(int code)
	{
		  IllegalArgumentException exp = assertThrows(IllegalArgumentException.class, ()->{
				service.getEmployee(code);
			});
			
		   assertEquals(exp.getMessage(), "Invalid Code");
		   Mockito.verify(empRepo, Mockito.never()).findById(Mockito.anyInt());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {10000,150})
	public void getEmployee_EmployeeNotFound(int code)
	{
		 
		String expectedmsg = "Employee "+code+" not found";
		
		// configure
		Mockito.when(empRepo.findById(code)).thenReturn(Optional.empty());
		
		
		NullPointerException exp = assertThrows(NullPointerException.class, ()->{
			service.getEmployee(code);
		});		
		
		assertEquals(exp.getMessage(), expectedmsg);
		Mockito.verify(empRepo,Mockito.times(1)).findById(Mockito.anyInt());
		
		
	}
	
	
	@Test
    void testLinkDocumentsWithEmployee_ValidCase() {
        // sample data
        int empCode = 101;
        int adharNumber = 12345;

        KYCDocuments doc = new KYCDocuments();
        doc.setAdharNumber(adharNumber);
        doc.setDocType("PAN");

        Employee emp = new Employee();
        emp.setEmpCode(empCode);
        emp.setEmpName("Ramesh");

        // Mock dependencies (helper methods)
        // Here we mock the internal methods using spy since they are inside same service
        EmployeeService spyService = Mockito.spy(service);

        Mockito.doReturn(emp).when(spyService).getEmployee(empCode);
        Mockito.doReturn(doc).when(spyService).getDocs(adharNumber);

        // call method
        Employee result = spyService.linkDocumentsWithEmployee(adharNumber, empCode);

        // assertions
        assertNotNull(result);
        assertEquals(empCode, result.getEmpCode());
        assertEquals(doc, result.getEmpDocs());

        // verify internal calls
        Mockito.verify(spyService, Mockito.times(1)).getEmployee(empCode);
        Mockito.verify(spyService, Mockito.times(1)).getDocs(adharNumber);
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}//end test class











