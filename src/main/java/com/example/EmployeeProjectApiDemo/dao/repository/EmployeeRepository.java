package com.example.EmployeeProjectApiDemo.dao.repository;

import com.example.EmployeeProjectApiDemo.dao.repository.entity.EmployeeEntity;
import com.example.EmployeeProjectApiDemo.service.EmployeeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Integer> {
}
