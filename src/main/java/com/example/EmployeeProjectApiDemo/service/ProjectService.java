package com.example.EmployeeProjectApiDemo.service;

import com.example.EmployeeProjectApiDemo.dao.repository.ProjectRepository;
import com.example.EmployeeProjectApiDemo.dao.repository.dto.ProjectDto;
import com.example.EmployeeProjectApiDemo.dao.repository.entity.ProjectEntity;
import com.example.EmployeeProjectApiDemo.exception.NotFoundException;
import com.example.EmployeeProjectApiDemo.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;
    private ProjectMapper projectMapper;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    public List<ProjectDto> getAll() {
        return projectMapper.projectEntityToProjectDtoList(projectRepository.findAll());
    }

    public ProjectDto getById(int id) {
        ProjectEntity result = projectRepository.findById(id).orElseThrow(() -> new NotFoundException("Project not found with id " + id));
        return projectMapper.projectEntityToProjectDto(result);
    }

    public ProjectDto add(ProjectDto projectDto) {
        var newProject = projectMapper.projectDtoToProjectEntity(projectDto);
        projectRepository.save(newProject);
        return projectDto;
    }

    public ProjectDto update(int projectId, ProjectDto projectDto) {
        var oldProject = projectRepository.findById(projectId).orElseThrow(() -> new NotFoundException("Project not found with id " + projectId));
        var newProject = projectMapper.projectDtoToProjectEntity(projectDto);
        newProject.setId(oldProject.getId());
        projectRepository.save(newProject);
        return projectDto;
    }
}
