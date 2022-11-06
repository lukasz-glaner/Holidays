package com.project.holidays.web;

import com.project.holidays.domain.employee.Employee;
import com.project.holidays.domain.employee.EmployeeService;
import com.project.holidays.domain.employee.dto.EmployeeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<EmployeeDto> getEmployeeByEmail(@RequestParam String email) {
        return employeeService.findEmployeeByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/employees/id")
    public ResponseEntity<EmployeeDto> getEmployeeById(@RequestParam Long id) {
        return employeeService.findEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
