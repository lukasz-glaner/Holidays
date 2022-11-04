package com.project.holidays.domain.holiday;

import com.project.holidays.domain.employee.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HolidayService {
    private final HolidayRepository holidayRepository;
    private final EmployeeRepository employeeRepository;


    public HolidayService(HolidayRepository holidayRepository, EmployeeRepository employeeRepository) {
        this.holidayRepository = holidayRepository;
        this.employeeRepository = employeeRepository;
    }

    public Optional<Holiday> findHolidayById(Long id) {
        return holidayRepository.findById(id);
    }

    public List<Holiday> findApproveHolidays() {
        return holidayRepository.findHolidaysByIsApprovedIsTrue();
    }

    public Holiday createHoliday(Holiday holidayToAdd) {
        Holiday savedHoliday = holidayRepository.save(holidayToAdd);
        employeeRepository.findById(1L).orElseThrow().getHolidays().add(savedHoliday);
        return savedHoliday;
    }
}
