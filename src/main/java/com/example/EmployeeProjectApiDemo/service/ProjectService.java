package com.example.EmployeeProjectApiDemo.service;

import com.example.EmployeeProjectApiDemo.dao.repository.ProjectRepository;
import com.example.EmployeeProjectApiDemo.dao.repository.entity.ProjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectEntity> getAll() {
        return projectRepository.findAll();
    }

    public ProjectEntity getById(int id) {
        Optional<ProjectEntity> result = projectRepository.findById(id);
        return result.isPresent() ? result.get() : null;
    }

    public ProjectEntity add(ProjectEntity projectEntity) {
        return projectRepository.save(projectEntity);
    }

}
