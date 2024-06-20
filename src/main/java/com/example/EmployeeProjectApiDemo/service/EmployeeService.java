package com.example.EmployeeProjectApiDemo.service;

import com.example.EmployeeProjectApiDemo.dao.repository.EmployeeRepository;
import com.example.EmployeeProjectApiDemo.dao.repository.ProjectRepository;
import com.example.EmployeeProjectApiDemo.dao.repository.dto.EmployeeDto;
import com.example.EmployeeProjectApiDemo.dao.repository.dto.UpdateEmployeeDto;
import com.example.EmployeeProjectApiDemo.exception.EmployeeNotFoundException;
import com.example.EmployeeProjectApiDemo.exception.ProjectNotFoundException;
import com.example.EmployeeProjectApiDemo.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private ProjectRepository projectRepository;
    private EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ProjectRepository projectRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.employeeMapper = employeeMapper;
    }

    public List<EmployeeDto> getAll(){
        var employees = employeeRepository.findAll();
        return employeeMapper.employeeListToEmployeeDtoList(employees);
    }

    public EmployeeDto getById(int id) {
        var result = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id : " + id));
        return employeeMapper.employeeToEmployeeDto(result);
    }

    public EmployeeDto add(UpdateEmployeeDto updateEmployeeDto) {
        var newEmployee = employeeMapper.updateEmployeeDtoToEmployee(updateEmployeeDto);
        return employeeMapper.employeeToEmployeeDto(employeeRepository.save(newEmployee));
    }

    public UpdateEmployeeDto update(int id, UpdateEmployeeDto newEmployee) {
        var result = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id : " + id));
        var updatedEmployee = employeeMapper.updateEmployeeDtoToEmployee(newEmployee);
        updatedEmployee.setId(result.getId());
        employeeRepository.save(updatedEmployee);
        return newEmployee;
    }

    public EmployeeDto assignProject(int projectId,int employeeId) {
        var project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project not found with id : " + projectId));

        var employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id : " + employeeId));

        var existingProjects = employee.getProjects();

        if (existingProjects.contains(project)) {
            throw new RuntimeException("Project already exists with id : " + projectId);
        }

        employee.addProject(project);
        employeeRepository.save(employee);
        return employeeMapper.employeeToEmployeeDto(employee);
    }

    public EmployeeDto deleteProject(int projectId,int employeeId) {
        var project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project not found with id : " + projectId));

        var employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id : " + employeeId));

        var existingProjects = employee.getProjects();

        if (!existingProjects.contains(project)) {
            throw new RuntimeException("Project does not exist with id : " + projectId);
        }
        employee.removeProject(project);
        employeeRepository.save(employee);
        return employeeMapper.employeeToEmployeeDto(employee);
    }
}
