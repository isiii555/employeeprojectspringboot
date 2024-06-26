package com.example.EmployeeProjectApiDemo.dao.repository.dto;

import com.example.EmployeeProjectApiDemo.dao.repository.entity.ProjectEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class EmployeeDto {
    private String firstName;

    private String lastName;

    private String email;

    private String jobTitle;

    private LocalDate hireDate;

    private List<ProjectEntity> projects;
}
