package com.example.EmployeeProjectApiDemo.dao.repository.dto;

import com.example.EmployeeProjectApiDemo.dao.repository.entity.ProjectEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDto {
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String jobTitle;

    private LocalDate hireDate;

    private List<ProjectEntity> projects;
}
