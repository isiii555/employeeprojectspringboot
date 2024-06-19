package com.example.EmployeeProjectApiDemo.controller;

import com.example.EmployeeProjectApiDemo.dao.repository.entity.ProjectEntity;
import com.example.EmployeeProjectApiDemo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class ProjectController {
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(path = "/projects")
    public List<ProjectEntity> getAll() {
        return projectService.getAll();
    }

    @GetMapping(path = "/projects/{projectId}")
    public ProjectEntity getById(@PathVariable int projectId) {
        return projectService.getById(projectId);
    }
}
