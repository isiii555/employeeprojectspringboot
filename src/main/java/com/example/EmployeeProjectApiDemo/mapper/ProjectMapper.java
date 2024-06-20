package com.example.EmployeeProjectApiDemo.mapper;

import com.example.EmployeeProjectApiDemo.dao.repository.dto.ProjectDto;
import com.example.EmployeeProjectApiDemo.dao.repository.entity.ProjectEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectEntity projectDtoToProjectEntity(ProjectDto projectDto);

    ProjectDto projectEntityToProjectDto(ProjectEntity projectEntity);

    List<ProjectDto> projectEntityToProjectDtoList(List<ProjectEntity> projectEntities);

}
