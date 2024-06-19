package com.example.EmployeeProjectApiDemo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeErrorResponse {
    private int status;
    private String message;
    private long timestamp;
}
