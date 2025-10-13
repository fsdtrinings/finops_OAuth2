package com.mkj.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mkj.app.entity.Project;
import com.mkj.app.service.hrservice.ProjectService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/pm")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	public ProjectController() {
		System.out.println("Project controller constructor");
	}

	
	@PostMapping("/project")
	public String saveProject(@RequestBody Project p)
	{
		System.out.println("-->> Project create : "+p);
		return projectService.createProject(p);
	}
	
	@GetMapping("/project/{code}")
	public Project getProjectbyCode(@PathVariable int code)
	{
		
		return projectService.extractProjectByCode(code);
	}
	
	
	@PutMapping("/project/link")
	public String linkEmployeeWithProject(@RequestParam int pcode,
										@RequestParam int ecode)
	{
		
		String msg = projectService.linkEmployeeWithProject(pcode, ecode);
		
		return msg;
	}
}//end class







































