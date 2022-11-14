package com.project.holidays.domain.employee;

import com.project.holidays.domain.employee.dto.EmployeeDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepositoryMock;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindByEmailAndReturnEmployee() {
        //given
        Employee employee = new Employee("admin@holidays.pl", "adminpass", "admin", "adminowski", 10);
        employee.setId(1L);
        Mockito.when(employeeRepositoryMock.findByEmail("admin@holidays.pl")).thenReturn(Optional.of(employee));
        EmployeeService employeeService = new EmployeeService(employeeRepositoryMock);

        //when
        EmployeeDto employeeDtoResult = employeeService.findEmployeeByEmail("admin@holidays.pl").orElseThrow();
        //then
        Assertions.assertEquals(1L, employeeDtoResult.getId());
    }
}
