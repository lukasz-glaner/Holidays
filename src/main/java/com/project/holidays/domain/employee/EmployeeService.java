package com.project.holidays.domain.employee;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

private final EmployeeRepository employeeRepository;


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public Optional<Employee> findEmployeeByEmail(String email){
        return employeeRepository.findByEmail(email);
    }

    public Optional<Employee> findEmployeeById(Long id) {
        System.out.println("Byłem w poszukiwanie employee");
        return employeeRepository.findById(id);
    }
}
