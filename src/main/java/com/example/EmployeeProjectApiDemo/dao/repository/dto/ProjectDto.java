package com.example.EmployeeProjectApiDemo.dao.repository.dto;

import com.example.EmployeeProjectApiDemo.dao.repository.entity.EmployeeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {

    private String name;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

}
