package com.project.holidays.domain.employee;

import com.project.holidays.domain.employee.dto.EmployeeCredentialsDto;
import com.project.holidays.domain.employee.dto.EmployeeDto;
import com.project.holidays.domain.employee.dto.EmployeeRegistrationDto;
import com.project.holidays.domain.employee.mapper.EmployeeCredentialsDtoMapper;
import com.project.holidays.domain.employee.mapper.EmployeeDtoMapper;
import com.project.holidays.domain.employee.mapper.EmployeeRegistrationDtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<EmployeeDto> findEmployeeByEmail(String email) {
        Optional<Employee> employee = employeeRepository.findByEmail(email);
        return employee.map(EmployeeDtoMapper::map);
    }

    public Optional<EmployeeDto> findEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(EmployeeDtoMapper::map);
    }

    @Transactional
    public EmployeeDto registerEmployee(EmployeeRegistrationDto registrationDto) {
        Employee employee = EmployeeRegistrationDtoMapper.map(registrationDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeDtoMapper.map(savedEmployee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public Optional<EmployeeCredentialsDto> findCredentialsByEmail(String email){
        return employeeRepository.findByEmail(email)
                .map(EmployeeCredentialsDtoMapper::map);
    }
}
