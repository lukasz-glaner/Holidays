package com.project.holidays.domain.holiday;

import com.project.holidays.domain.employee.EmployeeRepository;
import com.project.holidays.domain.holiday.dto.HolidayDto;
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

    public Optional<HolidayDto> findHolidayByIdReturnDto(Long id) {
        return this.findHolidayById(id).map(HolidayDtoMapper::map);
    }

    public List<HolidayDto> findApprovedHolidays() {
        List<Holiday> holidayList = holidayRepository.findHolidaysByApprovedIsTrue();
        return holidayList
                .stream()
                .map(HolidayDtoMapper::map)
                .toList();
    }

    public HolidayDto createHoliday(Holiday holidayToAdd) {
        Holiday savedHoliday = holidayRepository.save(holidayToAdd);
        employeeRepository.findById(1L).orElseThrow().getHolidays().add(savedHoliday);
        return HolidayDtoMapper.map(savedHoliday);
    }

    public void updateHoliday(Holiday holiday) {
        holidayRepository.save(holiday);
    }

    public void deleteHoliday(Long id){
        holidayRepository.deleteById(id);
    }
}
