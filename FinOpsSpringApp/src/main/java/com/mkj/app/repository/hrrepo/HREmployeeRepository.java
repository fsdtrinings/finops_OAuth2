package com.mkj.app.repository.hrrepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mkj.app.entity.Employee;

@Repository
public interface HREmployeeRepository extends JpaRepository<Employee, Integer>
{

	/*findBy<PropertyName>*/
	
	// select * from employee where techname = ?
	public List<Employee> findByTechName(String techName);
	
	public List<Employee> findBySalaryBetween(int s1,int s2);
	
	// public List<Employee> findByTechNameAndDesignation(String techname,String designation);
	
	// public List<Employee> findBySalaryGreaterThan(int amount);
	
	// select * from employee where name like "x%";
	// public List<Employee> findByEmpNameContaining(String stringToken); "%x%"
	// public List<Employee> findByEmpNameStartsWith(String stringToken);  "x%"
	// public List<Employee> findByEmpNameEndWith(String stringToken); "%x"
	
	
	/*countBy<properyname>*/
	// public int countByEmployee();
	// public int countByTechName(String techname);
	
	
	// boolean 
	// public boolean    isNull or isNotNull
	// public boolean findByTechNameisNull();


	// Inner Query for Employee <-> KYCDocument 
	//  get Employee based on AdharNumber = ?
	public Employee findByEmpDocsAdharNumber(int filterAdharNumber);

}//end class



















