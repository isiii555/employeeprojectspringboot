package com.example.EmployeeProjectApiDemo.mapper;

import com.example.EmployeeProjectApiDemo.dao.repository.dto.EmployeeDto;
import com.example.EmployeeProjectApiDemo.dao.repository.dto.UpdateEmployeeDto;
import com.example.EmployeeProjectApiDemo.dao.repository.entity.EmployeeEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto employeeToEmployeeDto(EmployeeEntity employeeEntity);

    EmployeeEntity employeeDtoToEmployee(EmployeeDto employeeDto);

    EmployeeEntity updateEmployeeDtoToEmployee(UpdateEmployeeDto employeeDto);

    List<EmployeeDto> employeeListToEmployeeDtoList(List<EmployeeEntity> employees);
}
