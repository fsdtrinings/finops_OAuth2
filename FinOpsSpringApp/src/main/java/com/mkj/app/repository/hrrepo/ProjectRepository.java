package com.mkj.app.repository.hrrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mkj.app.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> 
{

}
