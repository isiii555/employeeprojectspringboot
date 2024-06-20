package com.example.EmployeeProjectApiDemo.controller;

import com.example.EmployeeProjectApiDemo.dao.repository.dto.EmployeeDto;
import com.example.EmployeeProjectApiDemo.dao.repository.dto.UpdateEmployeeDto;
import com.example.EmployeeProjectApiDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/employees")
    public List<EmployeeDto> getAll() {
        return employeeService.getAll();
    }

    @GetMapping(path = "/employees/{employeeId}")
    public EmployeeDto getById(@PathVariable int employeeId) {
        return employeeService.getById(employeeId);
    }

    @PostMapping(path = "/employees")
    public EmployeeDto add(@RequestBody UpdateEmployeeDto updateEmployeeDto) {
        return employeeService.add(updateEmployeeDto);
    }

    @PutMapping(path = "/employees/{employeeId}")
    public UpdateEmployeeDto update(@PathVariable int employeeId, @RequestBody UpdateEmployeeDto employee) {
        return employeeService.update(employeeId,employee);
    }

    @PutMapping(path = "/employees/{employeeId}/projects/{projectId}")
    public EmployeeDto assignProjectToEmployee(@PathVariable int employeeId, @PathVariable int projectId) {
        return employeeService.assignProject(projectId,employeeId);
    }

    @DeleteMapping(path = "/employees/{employeeId}/projects/{projectId}")
    public EmployeeDto deleteProjectFromEmployee(@PathVariable int employeeId, @PathVariable int projectId) {
        return employeeService.deleteProject(projectId,employeeId);
    }
}
