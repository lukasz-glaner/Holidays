package com.project.holidays.domain.employee;

import com.project.holidays.domain.employee.dto.EmployeeRegistrationDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<Employee> findEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    public Optional<Employee> findEmployeeById(Long id) {
        System.out.println("Byłem w poszukiwanie employee");
        return employeeRepository.findById(id);
    }

    @Transactional
    public Employee registerEmployee(EmployeeRegistrationDto registrationDto) {
        Employee employee = new Employee();
        employee.setEmail(registrationDto.getEmail());
        employee.setPassword(registrationDto.getPassword());
        employee.setFirstName(registrationDto.getFirstName());
        employee.setLastName(registrationDto.getLastName());
        return employeeRepository.save(employee);
    }
}
