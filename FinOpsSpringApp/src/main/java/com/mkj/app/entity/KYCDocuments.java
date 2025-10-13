package com.mkj.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class KYCDocuments 
{
	@Id
	private int adharNumber;
	
	private int panNumber;
	

}
