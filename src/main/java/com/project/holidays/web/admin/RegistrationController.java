package com.project.holidays.web.admin;

import com.project.holidays.domain.employee.EmployeeService;
import com.project.holidays.domain.employee.dto.EmployeeDto;
import com.project.holidays.domain.employee.dto.EmployeeRegistrationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class RegistrationController {
    private final EmployeeService employeeService;

    public RegistrationController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/registration")
    public ResponseEntity<EmployeeDto> register(@RequestBody @Valid EmployeeRegistrationDto registrationDto) {
        EmployeeDto registeredEmployee = employeeService.registerEmployee(registrationDto);
        return ResponseEntity.created(URI.create("/employees/id?id=" + registeredEmployee.getId()))
                .body(registeredEmployee);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }
}
