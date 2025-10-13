package com.mkj.app.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Schema(description = "Details about the Employee class in the system")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Schema(description = "Employee code , Unique identification of an employee")
	private int empCode;
	
	private String empName;
	@Schema(description = "Value [java , SFDC , AWS]")
	private String techName;
	@Schema(description = "Value [developer , associate , consultant, Project Manager]")
	private String designation;
	
	private int salary;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "docid")
	private KYCDocuments empDocs;
	
	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "profileId")
	private Profile profile;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="projectid")
	private Project project;
}














