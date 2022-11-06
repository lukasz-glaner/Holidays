package com.project.holidays.domain.employee;

import com.project.holidays.domain.employee.dto.EmployeeDto;

import java.util.Optional;

public class EmployeeDtoMapper {

    public static EmployeeDto map(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPassword(employee.getPassword());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setHolidays(employee.getHolidays());
        return employeeDto;
    }

}
