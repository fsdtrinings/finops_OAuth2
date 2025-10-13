package com.mkj.app.service.hrservice;

import org.springframework.stereotype.Service;

import com.mkj.app.entity.Project;

@Service
public interface ProjectService {

	public String createProject(Project project);
	public Project extractProjectByCode(int pcode);
	public String linkEmployeeWithProject(int pcode,int empCode);
	
}
