package com.example.EmployeeProjectApiDemo.service;

import com.example.EmployeeProjectApiDemo.dao.repository.EmployeeRepository;
import com.example.EmployeeProjectApiDemo.dao.repository.entity.EmployeeEntity;
import com.example.EmployeeProjectApiDemo.dao.repository.dto.EmployeeDto;
import com.example.EmployeeProjectApiDemo.exception.EmployeeNotFoundException;
import com.example.EmployeeProjectApiDemo.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
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

    public EmployeeDto add(EmployeeEntity employee) {
        var addedEmployee = employeeRepository.save(employee);
        return employeeMapper.employeeToEmployeeDto(addedEmployee);
    }

    public EmployeeDto update(int id,EmployeeDto newEmployee) {
        var result = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id : " + id));
        var updatedEmployee = employeeMapper.employeeDtoToEmployee(newEmployee);
        updatedEmployee.setId(result.getId());
        employeeRepository.save(updatedEmployee);
        return newEmployee;
    }

}
