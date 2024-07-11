package com.example.EmployeeProjectApiDemo.service

import com.example.EmployeeProjectApiDemo.dao.repository.EmployeeRepository
import com.example.EmployeeProjectApiDemo.dao.repository.entity.EmployeeEntity
import com.example.EmployeeProjectApiDemo.exception.NotFoundException
import com.example.EmployeeProjectApiDemo.helper.ProjectHelper
import com.example.EmployeeProjectApiDemo.mapper.EmployeeMapper
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class EmployeeServiceTest extends Specification {
    private ProjectHelper projectHelper
    private EmployeeMapper employeeMapper
    private EmployeeRepository employeeRepository

    private EmployeeService employeeService

    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    void setup() {
        projectHelper = Mock()
        employeeMapper = Mock()
        employeeRepository = Mock()
        employeeService = new EmployeeService(employeeRepository, projectHelper, employeeMapper)
    }

    def "getById success"() {
        given:
        def id = random.nextInt()
        def entity = random.nextObject(EmployeeEntity)

        when:
        def result = employeeService.getById(id)

        then:
        1 * employeeRepository.findById(id) >> Optional.of(entity)

        result == employeeMapper.employeeToEmployeeDto(entity)
    }

    def "getById employee not found"() {
        given:
        def id = random.nextInt()

        when:
        employeeService.getById(id)

        then:
        1 * employeeRepository.findById(id) >> Optional.empty()
        0 * employeeMapper.employeeToEmployeeDto(_)

        def ex = thrown(NotFoundException)
        ex.message == "EMPLOYEE_NOT_FOUND_" + id
    }
}
