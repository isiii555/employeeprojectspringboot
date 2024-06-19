package com.example.EmployeeProjectApiDemo.controller;

import com.example.EmployeeProjectApiDemo.dao.repository.dto.EmployeeDto;
import com.example.EmployeeProjectApiDemo.dao.repository.entity.EmployeeEntity;
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
    public EmployeeDto add(@RequestBody EmployeeEntity employee) {
        return employeeService.add(employee);
    }

    @PutMapping(path = "/employees/{employeeId}")
    public EmployeeDto update(@PathVariable int employeeId, @RequestBody EmployeeDto employee) {
        return employeeService.update(employeeId,employee);
    }

}
