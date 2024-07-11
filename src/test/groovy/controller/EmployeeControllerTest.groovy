package controller

import com.example.EmployeeProjectApiDemo.controller.EmployeeController
import com.example.EmployeeProjectApiDemo.controller.GlobalExceptionHandler
import com.example.EmployeeProjectApiDemo.dao.repository.dto.EmployeeDto
import com.example.EmployeeProjectApiDemo.service.EmployeeService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import java.time.LocalDate

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

class EmployeeControllerTest extends Specification {
    private MockMvc mockMvc
    private EmployeeService employeeService
    private ObjectMapper objectMapper
    private EmployeeController controller

    def url = "/api/v1/employees"

    def setup() {
        objectMapper = new ObjectMapper()
        objectMapper.registerModule(new JavaTimeModule())
        employeeService = Mock()
        controller = new EmployeeController(employeeService)
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build()
    }

    def "should return given employee"() {
        given:
        def employeeDto = new EmployeeDto("Kanan",
                "Fatullayev",
                "kananfatullayev@gmail.com",
                "software dev",
                LocalDate.of(2020, 6, 21), null)

        def expectedJson = '''
                            {
                                "firstName": "Kanan",
                                "lastName": "Fatullayev",
                                "email": "kananfatullayev@gmail.com",
                                "jobTitle": "software dev",
                                "hireDate": [2020,6,21],
                                "projects": null
                            }
        '''
        def employeeId = 1

        when:
        def result = mockMvc.perform(get(url + "/{employeeId}", employeeId)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().response

        then:
        1 * employeeService.getById(employeeId) >> employeeDto

        result.status == HttpStatus.OK.value()
        println(result.getContentAsString())
        println(objectMapper.writeValueAsString(employeeDto))

        JSONAssert.assertEquals(expectedJson,
                result.getContentAsString(), false)
    }
}
