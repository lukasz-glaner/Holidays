package com.project.holidays.domain.employee.mapper;

import com.project.holidays.domain.employee.Employee;
import com.project.holidays.domain.employee.EmployeeRole;
import com.project.holidays.domain.employee.dto.EmployeeCredentialsDto;

import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeCredentialsDtoMapper {
    public static EmployeeCredentialsDto map(Employee employee) {
        String email = employee.getEmail();
        String password = employee.getPassword();
        Set<String> roles = employee.getRoles()
                .stream()
                .map(EmployeeRole::getName)
                .collect(Collectors.toSet());
        return new EmployeeCredentialsDto(email, password, roles);
    }
}
