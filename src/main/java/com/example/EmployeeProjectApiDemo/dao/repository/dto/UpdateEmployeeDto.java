package com.example.EmployeeProjectApiDemo.dao.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeDto {
    private String firstName;

    private String lastName;

    private String email;

    private String jobTitle;

    private LocalDate hireDate;
}
