package controller

import com.example.EmployeeProjectApiDemo.controller.EmployeeController
import com.example.EmployeeProjectApiDemo.controller.GlobalExceptionHandler
import com.example.EmployeeProjectApiDemo.dao.repository.EmployeeRepository
import com.example.EmployeeProjectApiDemo.dao.repository.ProjectRepository
import com.example.EmployeeProjectApiDemo.dao.repository.dto.EmployeeDto
import com.example.EmployeeProjectApiDemo.dao.repository.entity.EmployeeEntity
import com.example.EmployeeProjectApiDemo.helper.ProjectHelper
import com.example.EmployeeProjectApiDemo.mapper.EmployeeMapper
import com.example.EmployeeProjectApiDemo.service.EmployeeService
import com.fasterxml.jackson.databind.ObjectMapper
import org.mapstruct.factory.Mappers
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get


import java.time.LocalDate

class EmployeeControllerTest extends Specification{
    private MockMvc mockMvc
    private EmployeeService employeeService
    private ObjectMapper objectMapper
    private EmployeeController controller
    private EmployeeRepository employeeRepository
    private ProjectRepository projectRepository
    private ProjectHelper helper
    private EmployeeMapper mapper

    def url = "/api/v1/employees"

    def setup() {
        employeeRepository = Mock()
        projectRepository = Mock()
        objectMapper = new ObjectMapper()
        mapper = Mappers.getMapper(EmployeeMapper.class)
        helper = new ProjectHelper(projectRepository)
        employeeService = new EmployeeService(employeeRepository,helper,mapper)
        controller = new EmployeeController(employeeService)
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
        .setControllerAdvice(new GlobalExceptionHandler())
        .build()
    }

    def "should return given employee" () {
        given:
        def employee = new EmployeeEntity(2,"Kanan","Fatullayev","kananfatullayev@gmail.com","software dev",LocalDate.of(2024,6,21),null)

        def employeeDto = new EmployeeDto("Kanan","Fatullayev","kananfatullayev@gmail.com","software dev",LocalDate.of(2024,6,21),null)

        employeeRepository.findById(2) >> employee

        when:
        def result = mockMvc.perform (get("$url/{employeeId}",2)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().response

        then:
        1 * employeeService.getById(2) >> employeeDto
        result.status == HttpStatus.OK.value()
        JSONAssert.assertEquals(objectMapper.writeValueAsString(employeeDto),result.getContentAsString(),false)
    }
}
