package com.example.EmployeeProjectApiDemo.mapper;

import com.example.EmployeeProjectApiDemo.dao.repository.entity.EmployeeEntity;
import com.example.EmployeeProjectApiDemo.dao.repository.dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

//    @Mapping(target = "id",ignore = true)
    EmployeeDto employeeToEmployeeDto(EmployeeEntity employeeEntity);

    EmployeeEntity employeeDtoToEmployee(EmployeeDto employeeDto);

//    @Mapping(target = "id",ignore = true)
    List<EmployeeDto> employeeListToEmployeeDtoList(List<EmployeeEntity> employees);
}
