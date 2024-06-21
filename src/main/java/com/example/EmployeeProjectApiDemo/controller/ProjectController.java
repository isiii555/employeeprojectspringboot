package com.example.EmployeeProjectApiDemo.controller;

import com.example.EmployeeProjectApiDemo.dao.repository.dto.ProjectDto;
import com.example.EmployeeProjectApiDemo.mapper.ProjectMapper;
import com.example.EmployeeProjectApiDemo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectController {
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectDto> getAll() {
        return projectService.getAll();
    }

    @GetMapping("/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectDto getById(@PathVariable int projectId) {
        return projectService.getById(projectId);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDto add(@RequestBody ProjectDto projectDto) {
        return projectService.add(projectDto);
    }

    @PutMapping("/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectDto update(@PathVariable int projectId, @RequestBody ProjectDto projectDto) {
        return projectService.update(projectId, projectDto);
    }
}
