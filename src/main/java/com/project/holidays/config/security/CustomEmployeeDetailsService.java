package com.project.holidays.config.security;

import com.project.holidays.domain.employee.EmployeeService;
import com.project.holidays.domain.employee.dto.EmployeeCredentialsDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomEmployeeDetailsService implements UserDetailsService {
    private final EmployeeService employeeService;

    public CustomEmployeeDetailsService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return employeeService.findCredentialsByEmail(username)
                .map(this::createEmployeeDetails)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Employee with email %s not found exception", username)));
    }

    private UserDetails createEmployeeDetails(EmployeeCredentialsDto credentials) {
        return User.builder()
                .username(credentials.getEmail())
                .password(credentials.getPassword())
                .roles(credentials.getRoles().toArray(String[]::new))
                .build();
    }
}
