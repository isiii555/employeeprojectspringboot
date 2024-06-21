package com.example.EmployeeProjectApiDemo.helper;

import com.example.EmployeeProjectApiDemo.dao.repository.ProjectRepository;
import com.example.EmployeeProjectApiDemo.dao.repository.dto.ProjectDto;
import com.example.EmployeeProjectApiDemo.dao.repository.entity.ProjectEntity;
import com.example.EmployeeProjectApiDemo.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class ProjectHelper {
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectHelper(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ProjectEntity getById(int id) {
        return projectRepository.findById(id).orElseThrow(() -> new NotFoundException("Project not found with id " + id));
    }

}
