package com.project.holidays.domain.employee.mapper;

import com.project.holidays.domain.employee.Employee;
import com.project.holidays.domain.employee.dto.EmployeeRegistrationDto;

public class EmployeeRegistrationDtoMapper {

    public static Employee map(EmployeeRegistrationDto employeeRegistrationDto){
        Employee employee = new Employee();
        employee.setEmail(employeeRegistrationDto.getEmail());
        employee.setPassword(employeeRegistrationDto.getPassword());
        employee.setFirstName(employeeRegistrationDto.getFirstName());
        employee.setLastName(employeeRegistrationDto.getLastName());
        return employee;
    }
}
