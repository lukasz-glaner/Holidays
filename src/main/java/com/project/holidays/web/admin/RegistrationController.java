package com.project.holidays.web.admin;

import com.project.holidays.domain.employee.Employee;
import com.project.holidays.domain.employee.EmployeeService;
import com.project.holidays.domain.employee.dto.EmployeeRegistrationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

@Controller
public class RegistrationController {
    private final EmployeeService employeeService;

    public RegistrationController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/registration")
    public ResponseEntity<Employee> register(@RequestBody EmployeeRegistrationDto registrationDto){
        Employee registeredEmployee = employeeService.registerEmployee(registrationDto);
        return ResponseEntity.created(URI.create("/employees/id?id=" + registeredEmployee.getId()))
                .body(registeredEmployee);
    }
}
