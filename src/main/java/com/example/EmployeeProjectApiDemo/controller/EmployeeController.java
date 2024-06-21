package com.example.EmployeeProjectApiDemo.controller;

import com.example.EmployeeProjectApiDemo.dao.repository.dto.EmployeeDto;
import com.example.EmployeeProjectApiDemo.dao.repository.dto.UpdateEmployeeDto;
import com.example.EmployeeProjectApiDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDto> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto getById(@PathVariable int employeeId) {
        return employeeService.getById(employeeId);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto add(@RequestBody UpdateEmployeeDto updateEmployeeDto) {
        return employeeService.add(updateEmployeeDto);
    }

    @PutMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateEmployeeDto update(@PathVariable int employeeId, @RequestBody UpdateEmployeeDto employee) {
        return employeeService.update(employeeId, employee);
    }

    @PatchMapping("/{employeeId}/projects/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto assignProjectToEmployee(@PathVariable int employeeId, @PathVariable int projectId) {
        return employeeService.updateProject(projectId, employeeId);
    }

//    @PatchMapping("/employees/{employeeId}/projects/{projectId}")
//    public EmployeeDto deleteProjectFromEmployee(@PathVariable int employeeId, @PathVariable int projectId) {
//        return employeeService.deleteProject(projectId,employeeId);
//    }

}
