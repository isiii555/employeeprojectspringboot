package com.example.EmployeeProjectApiDemo.service;

import com.example.EmployeeProjectApiDemo.dao.repository.EmployeeRepository;
import com.example.EmployeeProjectApiDemo.dao.repository.ProjectRepository;
import com.example.EmployeeProjectApiDemo.dao.repository.dto.EmployeeDto;
import com.example.EmployeeProjectApiDemo.dao.repository.dto.UpdateEmployeeDto;
import com.example.EmployeeProjectApiDemo.exception.NotFoundException;
import com.example.EmployeeProjectApiDemo.helper.ProjectHelper;
import com.example.EmployeeProjectApiDemo.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private ProjectHelper projectHelper;
    private EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ProjectHelper projectHelper, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.projectHelper = projectHelper;
        this.employeeMapper = employeeMapper;
    }

    public List<EmployeeDto> getAll() {
        var employees = employeeRepository.findAll();
        return employeeMapper.employeeListToEmployeeDtoList(employees);
    }

    public EmployeeDto getById(int id) {
        var result = employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee not found with id : " + id));
        return employeeMapper.employeeToEmployeeDto(result);
    }

    public EmployeeDto add(UpdateEmployeeDto updateEmployeeDto) {
        var newEmployee = employeeMapper.updateEmployeeDtoToEmployee(updateEmployeeDto);
        return employeeMapper.employeeToEmployeeDto(employeeRepository.save(newEmployee));
    }

    public UpdateEmployeeDto update(int id, UpdateEmployeeDto newEmployee) {
        var result = employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee not found with id : " + id));
        var updatedEmployee = employeeMapper.updateEmployeeDtoToEmployee(newEmployee);
        updatedEmployee.setId(result.getId());
        employeeRepository.save(updatedEmployee);
        return newEmployee;
    }

    public EmployeeDto updateProject(int projectId, int employeeId) {
        var project = projectHelper.getById(projectId);

        var employee = employeeRepository.findById(employeeId).orElseThrow(() -> new NotFoundException("Employee not found with id : " + employeeId));

        var existingProjects = employee.getProjects();

        if (existingProjects.contains(project)) {
            existingProjects.remove(project);
        } else {
            existingProjects.add(project);
        }

        employee.setProjects(existingProjects);
        employeeRepository.save(employee);
        return employeeMapper.employeeToEmployeeDto(employee);
    }

}
