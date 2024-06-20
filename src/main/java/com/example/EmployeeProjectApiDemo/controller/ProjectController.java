package com.example.EmployeeProjectApiDemo.controller;

import com.example.EmployeeProjectApiDemo.dao.repository.dto.ProjectDto;
import com.example.EmployeeProjectApiDemo.dao.repository.entity.ProjectEntity;
import com.example.EmployeeProjectApiDemo.mapper.ProjectMapper;
import com.example.EmployeeProjectApiDemo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class ProjectController {
    private final ProjectMapper projectMapper;
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService, ProjectMapper projectMapper) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
    }

    @GetMapping(path = "/projects")
    public List<ProjectDto> getAll() {
        var projects = projectService.getAll();
        return projectMapper.projectEntityToProjectDtoList(projects);
    }

    @GetMapping(path = "/projects/{projectId}")
    public ProjectDto getById(@PathVariable int projectId) {
        return projectService.getById(projectId);
    }

    @PostMapping(path = "/projects")
    public ProjectDto add(@RequestBody ProjectDto projectDto) {
        return projectService.add(projectDto);
    }

    @PutMapping(path = "/projects/{projectId}")
    public ProjectDto update(@PathVariable int projectId, @RequestBody ProjectDto projectDto) {
        return projectService.update(projectId,projectDto);
    }
}
