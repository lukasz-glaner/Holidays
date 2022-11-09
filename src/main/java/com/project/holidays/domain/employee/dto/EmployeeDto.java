package com.project.holidays.domain.employee.dto;

import com.project.holidays.domain.holiday.Holiday;

import java.util.Set;

public class EmployeeDto {

    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Integer holidaysDaysAvailable;
    private Set<Holiday> holidays;

    public EmployeeDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Holiday> getHolidays() {
        return holidays;
    }

    public void setHolidays(Set<Holiday> holidays) {
        this.holidays = holidays;
    }

    public void setHolidaysDaysAvailable(Integer holidaysDaysAvailable) { this.holidaysDaysAvailable = holidaysDaysAvailable; }

    public Integer getHolidaysDaysAvailable() { return holidaysDaysAvailable; }
}
